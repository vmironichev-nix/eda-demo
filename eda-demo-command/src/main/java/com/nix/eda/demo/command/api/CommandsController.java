package com.nix.eda.demo.command.api;

import com.nix.eda.demo.command.service.OrderEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(method = RequestMethod.POST, path = "/commands")
public class CommandsController {
    private static final String ORDER_CREATED = "OrderPlaced";
    private static final String ORDER_CONFIRMED = "OrderProcessed";
    private static final String ORDER_CANCELLED = "OrderCancelled";
    private final OrderEventService orderEventService;

    public CommandsController(OrderEventService orderEventService) {
        this.orderEventService = orderEventService;
    }

    @RequestMapping(path = "place-order")
    public ResponseEntity placeOrder(@RequestBody com.nix.eda.demo.command.api.Command command) {
        var eventId = orderEventService.create(command.getId(), ORDER_CREATED, command);
        return ResponseEntity.ok(Map.of("id", eventId));
    }

    @RequestMapping(path = "process-order")
    public ResponseEntity processOrder(@RequestBody com.nix.eda.demo.command.api.Command command) {
        var eventId = orderEventService.create(command.getId(), ORDER_CONFIRMED, command);
        return ResponseEntity.ok(Map.of("id", eventId));
    }

    @RequestMapping(path = "cancel-order")
    public ResponseEntity cancelOrder(@RequestBody com.nix.eda.demo.command.api.Command command) {
        var eventId = orderEventService.create(command.getId(), ORDER_CANCELLED, command);
        return ResponseEntity.ok(Map.of("id", eventId));
    }
}
