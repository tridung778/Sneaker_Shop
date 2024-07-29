package com.example.Java6_ASM.services;

import java.util.List;

import com.example.Java6_ASM.models.Account;

public interface AccountService {
	Account findById(String username);

	List<Account> findAll();
}
