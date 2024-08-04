package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.repositories.AccountRepository;
import com.example.Java6_ASM.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public boolean isFieldExists(String fieldName, String value) {
        Optional<Account> account;
        switch (fieldName) {
            case "username":
                account = accountRepository.findByUsername(value);
                break;
            case "email":
                account = accountRepository.findByEmail(value);
                break;
            case "phone":
                account = accountRepository.findByPhone(value);
                break;
            default:
                throw new IllegalArgumentException("Field name not recognized");
        }
        return account.isPresent();
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.handelFindByUserName(username);
    }

    @Override
    public Account getInfoAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return accountRepository.handelFindByUserName(auth.getName());
    }

	@Override
	public Account update(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void delete(UUID id) {
		accountRepository.deleteById(id);
	}

    @Override
    public int totalAccount() {
        int total = 0;
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            total++;
        }
        return total;
    }
}
