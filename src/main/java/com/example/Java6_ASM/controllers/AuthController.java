package com.example.Java6_ASM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	@RequestMapping("/auth/login/form")
	public String form() {
		return "login/login";
	}

	@RequestMapping("/auth/login/success")
	public String success(Model md) {
		return "index";
	}

//	@RequestMapping("/auth/login/error")
//	public String error(Model md) {
//		md.addAttribute("message", "sai thong tin dang nhap");
//		return "forward:/auth/login/form";
//	}

	@RequestMapping("/auth/login/error")
	public String login(Model model) {
		model.addAttribute("page", "login/login");
		return "index";
	}

	@RequestMapping("/auth/logoff/success")
	public String logoff(Model md) {
		md.addAttribute("message", "dang xuat thanh cong");
		return "forward:/auth/login/form";
	}

}
