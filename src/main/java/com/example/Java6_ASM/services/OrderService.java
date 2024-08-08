package com.example.Java6_ASM.services;

import com.example.Java6_ASM.enums.OrderStatus;
import com.example.Java6_ASM.enums.PaymentMethod;
import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.models.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order createOrder(Account account, PaymentMethod paymentMethod, OrderStatus orderStatus, String address);

    List<Order> findByAccount(Account account);

    List<Order> findByUsername(String username);
    
    Order findById(UUID id);
    
    List<Order> findAll();

    int totalOrder();

    void updateOrderStatus(UUID id, OrderStatus orderStatus);
}
