package org.example.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Order;
import org.example.models.OrderDTO;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    private ObjectMapper objectMapper;
    private static final String ID = "/{id}";

    @Autowired
    public OrderController(OrderService service,
                           ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable UUID id) {
        Order order = service.getOrderById(id);
        return ResponseEntity.ok(objectMapper.convertValue(order, OrderDTO.class));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = objectMapper.convertValue(orderDTO, Order.class);
        Order createdOrder = service.createOrder(order);
        OrderDTO createdOrderDTO = objectMapper.convertValue(createdOrder, OrderDTO.class);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdOrderDTO.getId())
                        .toUri()
        ).body(createdOrderDTO);
    }


}
