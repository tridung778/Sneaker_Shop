package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    List<Cart> findAllByAccountId(UUID accountId);
}
