package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemTestSuite {

    @Autowired
    private OrderItemRepository orderItemRepository;

    //Given
    OrderItem orderItem = new OrderItem(null, null, 11);

    @Test
    public void shouldCreateOrderItem() {
        //Given

        //When
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        Long savedOrderItemId = savedOrderItem.getId();

        //Then
        assertTrue(orderItemRepository.existsById(savedOrderItemId));

        //CleanUp
        orderItemRepository.deleteById(savedOrderItemId);
    }

    @Test
    public void shouldReadOrderItem() {
        //Given
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        Long savedOrderItemId = savedOrderItem.getId();

        //When
        Optional<OrderItem> readsavedOrderItem = orderItemRepository.findById(savedOrderItemId);

        //Then
        assertTrue(readsavedOrderItem.isPresent());
        assertNull(savedOrderItem.getOrder());
        assertNull(savedOrderItem.getProduct());
        assertEquals(11, savedOrderItem.getQuantity());

        //CleanUp
        orderItemRepository.deleteById(savedOrderItemId);
    }

    @Test
    public void shouldDeleteOrderItem() {
        //Given
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        Long savedOrderItemId = savedOrderItem.getId();

        //When
        orderItemRepository.deleteById(savedOrderItemId);

        //Then
        assertFalse(orderItemRepository.existsById(savedOrderItemId));
    }
}