package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    //Given
    private Order order = new Order(null);
    private OrderItem orderItem1 = new OrderItem(order, null, 11);
    private OrderItem orderItem2 = new OrderItem(order, null, 12);

    @Test
    public void testShouldCreateOrder() {
        //Given

        //When
        Order savedOrder = orderRepository.save(order);
        savedOrder.getOrderItems().add(orderItem1);
        savedOrder.getOrderItems().add(orderItem2);
        Long savedOrderId = savedOrder.getId();

        List<OrderItem> orderItems = orderItemRepository.findAll();
        Long savedOrderItem1Id = orderItems.get(orderItems.size() - 2).getId();
        Long savedOrderItem2Id = orderItems.get(orderItems.size() - 1).getId();

        //Then
        assertTrue(orderRepository.existsById(savedOrderId));
        assertEquals(11, orderItems.get(orderItems.size() - 2).getQuantity());
        assertEquals(12, orderItems.get(orderItems.size() - 1).getQuantity());
        assertEquals(2, savedOrder.getOrderItems().size());

        //Cleanup
        orderRepository.deleteById(savedOrderId);
        orderItemRepository.deleteById(savedOrderItem1Id);
        orderItemRepository.deleteById(savedOrderItem2Id);
    }

    @Test
    public void testShouldReadOrder() {
        //Given
        Order savedOrder = orderRepository.save(order);
        savedOrder.getOrderItems().add(orderItem1);
        savedOrder.getOrderItems().add(orderItem2);
        Long savedOrderId = savedOrder.getId();

        List<OrderItem> orderItems = orderItemRepository.findAll();
        Long savedOrderItem1Id = orderItems.get(orderItems.size() - 2).getId();
        Long savedOrderItem2Id = orderItems.get(orderItems.size() - 1).getId();

        //When
        Optional<Order> readSavedOrder = orderRepository.findById(savedOrderId);
        Optional<OrderItem> redSavedOrderItem1 = orderItemRepository.findById(savedOrderItem1Id);
        Optional<OrderItem> redSavedOrderItem2 = orderItemRepository.findById(savedOrderItem2Id);

        //Then
        assertTrue(readSavedOrder.isPresent());
        assertTrue(redSavedOrderItem1.isPresent());
        assertTrue(redSavedOrderItem2.isPresent());
        assertEquals(null, savedOrder.getUser());
        assertEquals(2, savedOrder.getOrderItems().size());


        //Cleanup
        orderRepository.deleteById(savedOrderId);
        orderItemRepository.deleteById(savedOrderItem1Id);
        orderItemRepository.deleteById(savedOrderItem2Id);
    }

    @Test
    public void testShouldDeleteOrder() {
        //Given
        Order savedOrder = orderRepository.save(order);
        savedOrder.getOrderItems().add(orderItem1);
        savedOrder.getOrderItems().add(orderItem2);
        Long savedOrderId = savedOrder.getId();

        List<OrderItem> orderItems = orderItemRepository.findAll();
        Long savedOrderItem1Id = orderItems.get(orderItems.size() - 2).getId();
        Long savedOrderItem2Id = orderItems.get(orderItems.size() - 1).getId();

        //When
        orderRepository.deleteById(savedOrderId);
        orderItemRepository.deleteById(savedOrderItem1Id);
        orderItemRepository.deleteById(savedOrderItem2Id);

        //Then
        assertFalse(orderRepository.existsById(savedOrderId));
        assertFalse(orderItemRepository.existsById(savedOrderItem1Id));
        assertFalse(orderItemRepository.existsById(savedOrderItem2Id));
    }
}