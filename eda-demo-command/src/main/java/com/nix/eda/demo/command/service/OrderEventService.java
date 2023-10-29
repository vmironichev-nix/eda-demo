package com.nix.eda.demo.command.service;

import com.nix.eda.demo.command.dao.Event;
import com.nix.eda.demo.command.dao.EventsRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class OrderEventService {

    private final EventsRepository eventsRepository;

    private final OrderEventProducer orderEventProducer;

    private final OrderEventMapper orderEventMapper;

    public OrderEventService(EventsRepository eventsRepository, OrderEventProducer orderEventProducer, OrderEventMapper orderEventMapper) {
        this.eventsRepository = eventsRepository;
        this.orderEventProducer = orderEventProducer;
        this.orderEventMapper = orderEventMapper;
    }

    @Transactional
    public UUID create(UUID aggregateId, String type, Object payload) {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setAggregateId(aggregateId);
        event.setType(type);
        event.setPayload(payload);
        eventsRepository.save(event);
        orderEventProducer.send(orderEventMapper.map(event));
        return event.getId();
    }
}
