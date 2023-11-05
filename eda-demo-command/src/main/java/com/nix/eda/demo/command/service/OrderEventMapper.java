package com.nix.eda.demo.command.service;

import com.nix.eda.demo.command.api.Command;
import com.nix.eda.demo.command.dao.Event;
import com.nix.eda.demo.event.Attribute;
import com.nix.eda.demo.event.EventType;
import com.nix.eda.demo.event.OrderEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderEventMapper {

    public OrderEvent map(Event event) {
        var attributes = getAttributeList(event);
        return OrderEvent.newBuilder()
                .setId(event.getId())
                .setOrderId(event.getAggregateId())
                .setType(EventType.valueOf(event.getType()))
                .setTimestamp(System.currentTimeMillis())
                .setAttributes(attributes)
                .build();
    }

    private List<Attribute> getAttributeList(Event event) {
        return ((Command) event.getPayload()).getAttributes().entrySet()
                .stream()
                .map(entry -> Attribute.newBuilder()
                        .setName(entry.getKey())
                        .setValue(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }
}
