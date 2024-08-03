package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.DTO.StripeRequestDTO;
import com.example.Java6_ASM.DTO.StripeResponseDTO;
import com.example.Java6_ASM.enums.OrderStatus;
import com.example.Java6_ASM.enums.PaymentMethod;
import com.example.Java6_ASM.models.*;
import com.example.Java6_ASM.services.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Value("${stripe.api.publicKey}")
    private String publicKey;

    private final PaypalService paypalService;

    //    CRUD item in cart
    @GetMapping("{accountID}")
    public ResponseEntity<List<Cart>> getAllItem(@PathVariable("accountID") UUID accountID) {
        List<Cart> cartList = cartService.getAllItemInCart(accountID);
        return ResponseEntity.ok(cartList);
    }

    @DeleteMapping("/delete/{itemID}")
    public ResponseEntity<Cart> deleteItem(@PathVariable("itemID") UUID itemID) {
        cartService.deleteItemInCart(itemID);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/update/{itemID}")
    public ResponseEntity<Cart> reduceQuantity(@PathVariable("itemID") UUID itemID) {
        Cart cart = cartService.reduceQuantityItemInCart(itemID);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(@RequestParam("productID") UUID productID, @RequestParam("accountID") UUID accountID) {
        Product product = productService.findById(productID).get();

        Account account = accountService.findById(accountID);

        Cart item = new Cart();
        item.setName(product.getName());
        item.setPrice(product.getPrice());
        item.setThumbnail(product.getImage());
        item.setCategory(product.getCategory().getName());
        item.setAccount(account);
        item.setProductId(product.getId());
        Cart cart = cartService.addItemToCart(item);
        return ResponseEntity.ok(cart);
    }

    // Payment
    @PutMapping("/payment-cod")
    public ResponseEntity<String> payment() {
        pay(PaymentMethod.COD);
        return ResponseEntity.ok().body("Payment success");
    }

    //Stripe payment
    @PostMapping("/create-payment-intent")
    public ResponseEntity<StripeResponseDTO> createPaymentIntent(@RequestBody @Validated StripeRequestDTO request) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(request.getAmount() * 100L)
                .putMetadata("productName", request.getProductName())
                .setCurrency("usd")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .build();

        PaymentIntent intent = PaymentIntent.create(params);

        StripeResponseDTO responseDto = new StripeResponseDTO(intent.getId(), intent.getClientSecret());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // Paypal payment
    // sb-fo7f331992187@personal.example.com
    // r&o}V0Z>
    @RequestMapping("/payment-paypal")
    public Map<String, String> payment(@RequestParam("totalPrice") double totalPrice, @RequestParam("userName") String userName) {
        Map<String, String> response = new HashMap<>();
        try {
            String cancelUrl = "http://localhost:8080/payment/paypal/cancel";
            String successUrl = "http://localhost:8080/payment/paypal/success";
            Payment payment = paypalService.createPayment(
                    totalPrice,
                    "USD",
                    "PAYPAL",
                    "sale",
                    userName + " Thanh toán",
                    cancelUrl,
                    successUrl
            );
            pay(PaymentMethod.PAYPAL);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    response.put("redirectUrl", links.getHref());
                    return response;
                }
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        response.put("redirectUrl", "payment/paypal/error");
        return response;
    }

    @GetMapping("/payment/paypal/cancel")
    @ResponseBody
    public String cancel() {
        return "cancel";
    }

    @GetMapping("/payment/paypal/error")
    @ResponseBody
    public String error() {
        return "error";
    }

    public void pay(PaymentMethod paymentMethod) {
        List<Cart> cartList = cartService.getAllItemInCart(accountService.getInfoAuth().getId());
        Order order = orderService.createOrder(accountService.getInfoAuth(), paymentMethod, OrderStatus.PENDING, accountService.getInfoAuth().getAddress());
        cartList.forEach(cart -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPrice(cart.getPrice());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setProduct(productService.findById(cart.getProductId()).get());
            orderDetail.setOrder(order);
            orderDetailService.createOrderDetail(orderDetail);
        });
        cartService.deleteAllItemInCart();
    }

}
