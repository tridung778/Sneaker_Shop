package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.services.AccountService;
import com.example.Java6_ASM.services.CategoryService;
import com.example.Java6_ASM.services.OrderService;
import com.example.Java6_ASM.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AccountService accountService;

	@Autowired
	OrderService orderService;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("page", "components/home");
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		model.addAttribute("userInfo", accountService.getInfoAuth());
		return "index";
	}

	@RequestMapping("/{id}")
	public String productDetail(Model model, @PathVariable("id") UUID id) {
		Optional<Product> item = productService.findById(id);
		model.addAttribute("item", item.orElse(null));
		model.addAttribute("page", "product/product-detail");
		model.addAttribute("userInfo", accountService.getInfoAuth());
		return "index";
	}

	@RequestMapping("/category")
	public String productInCategory(Model model, @RequestParam("cid") Optional<UUID> cid) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "product/product-category");
		model.addAttribute("cates", categoryService.findAll());
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		return "index";
	}

	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("addProduct", "admin/home.html");
		return "admin-index";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "login/login");
		return "login/login";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "login/signup");
		return "login/signup";
	}

	@RequestMapping("/cart-index")
	public String cart(Model model) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "cart/cart-index");
		return "index";
	}

	@RequestMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "user/update-profile");
		return "index";
	}

	@RequestMapping("/orderList")
	public String orderList(Model model, HttpServletRequest request) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "order/list");
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "index";
	}

	@RequestMapping("/order")
	public String orderDetail(Model model, @RequestParam("id") UUID id) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "order/detail");
		model.addAttribute("order", orderService.findById(id));
		return "index";
	}

	@RequestMapping("/purchase")
	public String purchase(Model model) {
		model.addAttribute("userInfo", accountService.getInfoAuth());
		model.addAttribute("page", "order/purchase");
		return "index";
	}

}
