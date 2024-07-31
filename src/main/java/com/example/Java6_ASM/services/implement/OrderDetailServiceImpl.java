package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.OrderDetail;
import com.example.Java6_ASM.repositories.OrderDetailRepository;
import com.example.Java6_ASM.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}
