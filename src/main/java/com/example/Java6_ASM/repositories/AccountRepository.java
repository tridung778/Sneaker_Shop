package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
	@Query("select o from Account o where o.username like ?1")
	Account handelFindByUserName(String username);

	Optional<Account> findByUsername(String name);
	Optional<Account> findByEmail(String email);
	Optional<Account> findByPhone(String phone);
}
