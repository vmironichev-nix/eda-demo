package com.nix.eda.demo.command.service;

import com.nix.eda.demo.event.OrderEvent;

public interface OrderEventProducer {
    void send(OrderEvent event);
}
