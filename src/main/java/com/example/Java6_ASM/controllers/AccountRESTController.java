package com.example.Java6_ASM.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.services.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRESTController {
	@Autowired
	AccountService service;
	BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

	@GetMapping()
	public List<Account> getAll() {
		return service.findAll();
	}

	@PostMapping()
	public Account create(@RequestBody Account acc) {
		String rawPassword = acc.getPassword();
		String encodedPassword = pe.encode(rawPassword);
		acc.setPassword(encodedPassword);
		return service.createAccount(acc);
	}
}
