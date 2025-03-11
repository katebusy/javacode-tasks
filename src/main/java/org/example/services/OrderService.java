package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.entities.Order;
import org.example.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Order getOrderById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no order with this id: %s".formatted(id)));
    }

    @Transactional
    public Order createOrder(Order order) {
        return repository.save(order);
    }

}
