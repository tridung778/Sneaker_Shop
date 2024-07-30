package com.example.Java6_ASM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Java6_ASM.SecurityConfig;
import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.services.CategoryService;
import com.example.Java6_ASM.services.ProductService;

@Controller
public class SecurityController {
	@Autowired
	SecurityConfig service;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/security/login/form")
	public String loginForm(Model md) {
		md.addAttribute("message", "Vui Long dang nhap");
		return "login/login";
	}

	@RequestMapping("/security/login/success")
	public String success(Model model) {
		model.addAttribute("message", "success");
		model.addAttribute("page", "components/home");
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "index";
	}

	@RequestMapping("/security/login/error")
	public String error(Model md) {
		md.addAttribute("page", "login/signup");
		return "login/signup";
	}

	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model md) {
		md.addAttribute("message", "Khong co quyen truy xuat");
		return "security/login";
	}

	@RequestMapping("/security/logoff/success")
	public String logoff(Model md) {
		md.addAttribute("message", "Dang xuat thanh cong");
		return "security/login";
	}

}
