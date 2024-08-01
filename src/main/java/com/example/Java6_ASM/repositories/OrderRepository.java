package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByAccount(Account account);
}
