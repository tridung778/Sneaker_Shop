package com.example.Java6_ASM.services;

import com.example.Java6_ASM.enums.OrderStatus;
import com.example.Java6_ASM.enums.PaymentMethod;
import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.models.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Order createOrder(Account account, PaymentMethod paymentMethod, OrderStatus orderStatus, Date createdAt);

    List<Order> findByAccount(Account account);

}
