package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.OrderDetail;

public interface OrderDetailService {

    void createOrderDetail(OrderDetail orderDetail);

    double totalAmount();
}
