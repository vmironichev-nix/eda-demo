package com.nix.eda.demo.query.service;

import com.nix.eda.demo.query.dao.Order;
import com.nix.eda.demo.query.dao.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }
}
