package com.nix.eda.demo.query.service;

import com.nix.eda.demo.event.Attribute;
import com.nix.eda.demo.event.OrderEvent;
import com.nix.eda.demo.query.dao.Order;
import com.nix.eda.demo.query.dao.OrderItem;
import com.nix.eda.demo.query.dao.OrderStatus;
import org.apache.avro.util.Utf8;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.nix.eda.demo.query.dao.OrderStatus.CANCELLED;
import static com.nix.eda.demo.query.dao.OrderStatus.COMPLETED;
import static com.nix.eda.demo.query.dao.OrderStatus.NEW;

@Component
public class OrderMapper {

    public static final String TYPE = "type";
    public static final String QUANTITY = "quantity";
    public static final String PRICE_ID = "priceId";

    public Order map(OrderEvent event) {
        Order order = new Order();
        order.setId(event.getOrderId());
        order.setStatus(getStatus(event));
        List<Attribute> attributes = event.getAttributes();
        OrderItem item = getOrderItem(attributes);
        item.setOrder(order);
        order.setItems(List.of(item));
        return order;
    }

    private OrderItem getOrderItem(List<Attribute> attributes) {
        OrderItem item = new OrderItem();
        item.setId(UUID.randomUUID());
        var type = (Utf8) getAttributeValue(TYPE, attributes);
        item.setName(type.toString());
        Object quantity = getAttributeValue(QUANTITY, attributes);
        item.setQuantity((int) quantity);
        var priceId = (Utf8) getAttributeValue(PRICE_ID, attributes);
        item.setPriceId(priceId.toString());
        return item;
    }

    private Object getAttributeValue(String name, List<Attribute> attributes) {
        return attributes.stream()
                .filter(a -> name.equals(a.getName().toString()))
                .map(Attribute::getValue)
                .findFirst()
                .orElseThrow();
    }

    private static OrderStatus getStatus(OrderEvent event) {
        switch (event.getType()) {
            case OrderPlaced -> {
                return NEW;
            }
            case OrderCancelled -> {
                return CANCELLED;
            }
            case OrderProcessed -> {
                return COMPLETED;
            }
            default -> throw new IllegalStateException("Could not identify order status");
        }
    }
}
