package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    void createOrderDetail(OrderDetail orderDetail);

    double totalAmount();

    List<OrderDetail> findAll();
}
