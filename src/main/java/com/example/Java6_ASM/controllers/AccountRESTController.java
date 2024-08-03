package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable UUID id) {
        Account account = service.findById(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID id, @RequestBody Account updatedAccount) {
        Account existingAccount = service.findById(id);
        if (existingAccount == null) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật thông tin tài khoản
        existingAccount.setName(updatedAccount.getName());
        existingAccount.setEmail(updatedAccount.getEmail());
        existingAccount.setPhone(updatedAccount.getPhone());
        existingAccount.setPhoto(updatedAccount.getPhoto());

        Account savedAccount = service.createAccount(existingAccount);
        return ResponseEntity.ok(savedAccount);
    }
}
