package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.OrderDetail;
import com.example.Java6_ASM.repositories.OrderDetailRepository;
import com.example.Java6_ASM.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public double totalAmount() {
        double total = 0;

        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        for (OrderDetail orderDetail : orderDetails) {
            total += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        return total;
    }
}
