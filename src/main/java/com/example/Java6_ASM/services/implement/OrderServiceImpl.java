package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.enums.OrderStatus;
import com.example.Java6_ASM.enums.PaymentMethod;
import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.models.Order;
import com.example.Java6_ASM.repositories.OrderRepository;
import com.example.Java6_ASM.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Account account, PaymentMethod paymentMethod, OrderStatus orderStatus, String address) {
        Order order = new Order();
        order.setAddress(address);
        order.setCreatedAt(new Date());
        order.setAccount(account);
        order.setPaymentMethod(paymentMethod);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByAccount(Account account) {
        return orderRepository.findByAccount(account);
    }

	@Override
	public List<Order> findByUsername(String username) {
		return orderRepository.findByUsername(username);
	}

	@Override
	public Order findById(UUID id) {
		Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

    @Override
    public int totalOrder() {
        int total = 0;
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            total++;
        }
        return total;
    }
}
