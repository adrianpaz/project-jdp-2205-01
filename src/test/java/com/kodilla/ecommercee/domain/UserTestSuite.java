package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;
    //Given
    User user = new User("User", "Test",123L);
    Order order1 = new Order(user);
    Order order2 = new Order(user);

    @Test
    public void shouldCreateUser(){
        //Given

        //When
        User savedUser = userRepository.save(user);
        savedUser.getOrders().add(order1);
        savedUser.getOrders().add(order2);



        //Then
        assertTrue(userRepository.existsById(savedUser.getId()));
        assertTrue(orderRepository.existsById(order1.getId()));
        assertTrue(orderRepository.existsById(order2.getId()));

        //CleanUp
        userRepository.deleteById(savedUser.getId());
        orderRepository.deleteById(order1.getId());
        orderRepository.deleteById(order2.getId());
    }

    @Test
    public void shouldReadUser(){
        //Given
        User savedUser = userRepository.save(user);
        savedUser.getOrders().add(order1);
        savedUser.getOrders().add(order2);

        //When
        User readUser = userRepository.findById(savedUser.getId()).get();
        Order readOrder1 = userRepository.findById(savedUser.getId()).get().getOrders().iterator().next();
        Order readOrder2 = userRepository.findById(savedUser.getId()).get().getOrders().iterator().next();

        //Then
        assertEquals("User",readUser.getUsername());
        assertEquals("Test",readUser.getStatus());
        assertEquals(123L,readOrder1.getUser().getUserKey().longValue());
        assertEquals(123L,readOrder2.getUser().getUserKey().longValue());
        //CleanUp
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void shouldDeleteUser(){
        //Given
        User savedUser = userRepository.save(user);
        savedUser.getOrders().add(order1);
        savedUser.getOrders().add(order2);

        Long savedUserId = savedUser.getId();

        //When
        userRepository.deleteById(savedUser.getId());


        //Then
        assertFalse(userRepository.existsById(savedUserId));
    }








}
