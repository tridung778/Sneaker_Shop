package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.enums.OrderStatus;
import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByAccount(Account account);
    
    @Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);

    @Modifying
    @Query("UPDATE Order o SET o.orderStatus = :status WHERE o.id = :id")
    void updateOrderStatus(@Param("id") Long id, @Param("status") OrderStatus status);
}
