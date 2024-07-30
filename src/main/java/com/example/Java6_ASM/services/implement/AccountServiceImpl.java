package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.repositories.AccountRepository;
import com.example.Java6_ASM.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }
}
