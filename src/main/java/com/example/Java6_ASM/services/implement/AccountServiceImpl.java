package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.repositories.AccountRepository;
import com.example.Java6_ASM.services.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository dao;

	@Override
	public Account findById(String username) {
		return dao.findById(username);
	}

	public List<Account> findAll() {
		return dao.findAll();
	}
}
