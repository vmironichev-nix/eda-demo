package com.nix.eda.demo.query.api;

import com.nix.eda.demo.query.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "orders")
public class QueryRestController {

    private final OrderService orderService;

    public QueryRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findOrder(@PathVariable("id") UUID id) {
        return ResponseEntity.of(orderService.findOrder(id));
    }

    @GetMapping
    public ResponseEntity getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}

