package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {
	@Autowired
	ProductService productService;

	@RequestMapping("/")
	public String index(Model model, @RequestParam("cid") Optional<UUID> cid) {
		model.addAttribute("page", "components/home");
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		return "index";
	}

	@RequestMapping("/{id}")
	public String productDetail(Model model, @PathVariable("id") UUID id) {
		Optional<Product> item = productService.findById(id);
		model.addAttribute("item", item.orElse(null));
		model.addAttribute("page", "product/product-detail");
		return "index";
	}

	@RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("addProduct", "admin/home.html");
        return "admin-index";
    }

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("page", "login/login");
		return "login/login";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("page", "login/signup");
		return "login/signup";
	}

	@RequestMapping("/category")
	public String productCategory(Model model) {
		model.addAttribute("page", "product/product-category");
		return "index";
	}

	@RequestMapping("/cart-index")
	public String cart(Model model) {
		model.addAttribute("page", "cart/cart-index");
		return "index";
	}
}
