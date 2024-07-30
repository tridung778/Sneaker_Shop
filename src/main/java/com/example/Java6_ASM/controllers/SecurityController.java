package com.example.Java6_ASM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Java6_ASM.SecurityConfig;

@Controller
public class SecurityController {
	@Autowired
	SecurityConfig service;

	@RequestMapping("/security/login/form")
	public String loginForm(Model md) {
		md.addAttribute("message", "Vui Long dang nhap");
		return "login/login";
	}

	@RequestMapping("/security/login/success")
	public String success(Model md) {
		md.addAttribute("message", "success");
		// return "security/login";
		return "index";
	}

	@RequestMapping("/security/login/error")
	public String error(Model md) {
		return "login/login";
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
