package com.nix.eda.demo.query.service;

import com.nix.eda.demo.event.OrderEvent;
import com.nix.eda.demo.query.dao.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderEventsConsumer {

    private OrderMapper orderMapper;
    private OrderService orderService;

    public KafkaOrderEventsConsumer(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }


    @KafkaListener(topicPattern = "${messaging.events.topics.order:order-events-v1}", groupId = "consumer-eda-query-model")
    public void handle(ConsumerRecord<String, OrderEvent> consumerRecord) {
        OrderEvent event = consumerRecord. value();
        Order order = orderMapper.map(event);
        orderService.save(order);
    }
}
