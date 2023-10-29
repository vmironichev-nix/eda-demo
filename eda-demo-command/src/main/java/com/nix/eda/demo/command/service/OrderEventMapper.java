package com.nix.eda.demo.command.service;

import com.nix.eda.demo.command.dao.Event;
import com.nix.eda.demo.event.EventType;
import com.nix.eda.demo.event.OrderEvent;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderEventMapper {

    OrderEvent map(Event event) {
        return OrderEvent.newBuilder()
                .setId(event.getId())
                .setType(EventType.valueOf(event.getType()))
                .setTimestamp(System.currentTimeMillis())
                .setAttributes(List.of())
                .build();
    }
}
