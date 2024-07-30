package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.Cart;
import com.example.Java6_ASM.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("{id}")
    public ResponseEntity<List<Cart>> getAllItem(@PathVariable("id") UUID id) {
        List<Cart> cartList = cartService.getAllItemInCart(id);
        return ResponseEntity.ok(cartList);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(@RequestBody Cart item) {
        Cart cart = cartService.addItemToCart(item);
        return ResponseEntity.ok(cart);
    }


}
