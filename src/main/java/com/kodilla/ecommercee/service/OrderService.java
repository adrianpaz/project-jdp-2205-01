package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(final Long orderId) {
        orderRepository.deleteById(orderId);
    }
}