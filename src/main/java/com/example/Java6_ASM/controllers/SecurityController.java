package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.SecurityConfig;
import com.example.Java6_ASM.enums.Role;
import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.services.AccountService;
import com.example.Java6_ASM.services.CategoryService;
import com.example.Java6_ASM.services.ProductService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

	@Autowired
	private BCryptPasswordEncoder pe;

	@RequestMapping("/security/login/form")
	public String loginForm(Model md) {
		md.addAttribute("message", "Vui Lòng đăng nhập");
		return "login/login";
	}

	@RequestMapping("/login-success")
	public String success(Model model) {
		return "redirect:/";
	}

	@RequestMapping("/security/login/error")
	public String error(Model md) {
		md.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng");
		return "redirect:/login";
	}

	@RequestMapping("/unauthoried")
	public String unauthoried(Model md) {
		md.addAttribute("message", "Không có quyền truy xuất");
		return "security/login";
	}

	@RequestMapping("logoff-success")
	public String logoff(Model md) {
		md.addAttribute("message", "Đăng xuất thành công");
		return "redirect:/";
	}

	@RequestMapping("/security/login/success")
	public String successOAuth2(OAuth2AuthenticationToken oauth2, Model model) {
		
		String randomPassword = UUID.randomUUID().toString();
		String encodedPassword = pe.encode(randomPassword);

		Account account = new Account();
		account.setEmail(oauth2.getPrincipal().getAttribute("email"));
		account.setName(oauth2.getPrincipal().getAttribute("name"));
		account.setUsername(oauth2.getPrincipal().getAttribute("sub"));
		account.setPhoto(oauth2.getPrincipal().getAttribute("avatar"));
		account.setPassword(encodedPassword);
		account.setRole(Role.GUEST);
		accountService.createAccount(account);
		model.addAttribute("userInfo", accountService.getInfoAuth());
		return "redirect:/";
	}
}
