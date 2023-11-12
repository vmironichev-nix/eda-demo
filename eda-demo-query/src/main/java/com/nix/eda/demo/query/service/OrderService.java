package com.nix.eda.demo.query.service;

import com.nix.eda.demo.query.dao.Order;
import com.nix.eda.demo.query.dao.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findOrder(UUID id) {
        return orderRepository.findById(id);
    }

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }
}
