package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.SecurityConfig;
import com.example.Java6_ASM.services.AccountService;
import com.example.Java6_ASM.services.CategoryService;
import com.example.Java6_ASM.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@Autowired
	SecurityConfig service;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AccountService accountService;

	@RequestMapping("/security/login/form")
	public String loginForm(Model md) {
		md.addAttribute("message", "Vui Long dang nhap");
		return "login/login";
	}

	@RequestMapping("/login-success")
	public String success(Model model) {
		System.out.println("User info:" + accountService.getInfoAuth());
		return "redirect:/";
	}

	@RequestMapping("/security/login/error")
	public String error(Model md) {
		md.addAttribute("page", "login/signup");
		return "login/signup";
	}

	@RequestMapping("/unauthoried")
	public String unauthoried(Model md) {
		md.addAttribute("message", "Khong co quyen truy xuat");
		return "security/login";
	}

	@RequestMapping("logoff-success")
	public String logoff(Model md) {
		md.addAttribute("message", "Dang xuat thanh cong");
		return "redirect:/";
	}

}
