package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
	Account findById(String username);
}
