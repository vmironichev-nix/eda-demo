package com.nix.eda.demo.command.service;

import com.nix.eda.demo.event.OrderEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaOrderOrderEventProducer implements OrderEventProducer {

    @Value("${messaging.events.topics.order:order-events-v1}")
    private String orderEventsTopic;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public KafkaOrderOrderEventProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(OrderEvent event) {
        kafkaTemplate.send(orderEventsTopic, UUID.randomUUID().toString(), event);
    }
}
