package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.models.User;

import java.util.List;
import java.util.UUID;

public interface AccountService {

	List<Account> findAll();

	Account createAccount(Account account);

	Account findById(UUID id);

	Account findByUsername(String id);

	Account getInfoAuth();
	
	boolean isFieldExists(String fieldName, String value);

	Account update(Account account);
	
	void delete(UUID id);
}
