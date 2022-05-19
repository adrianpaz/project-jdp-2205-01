package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private GroupRepository groupRepository;

    Group group = new Group("TestGroup");
    Product product = new Product("TestProduct","Test", new BigDecimal("24.99"),group);

    CartItem cartItem1 = new CartItem(null,product,10);
    CartItem cartItem2 = new CartItem(null,product,8);
    OrderItem orderItem1 = new OrderItem(null,product,10);
    OrderItem orderItem2 = new OrderItem(null,product,8);



    @Test
    public void shouldCreateProduct(){
        //Given

        //When
        Group savedGroup = groupRepository.save(group);
        Product savedProduct = productRepository.save(product);

        savedGroup.getProducts().add(product);

        savedProduct.getCartItems().add(cartItem1);
        savedProduct.getCartItems().add(cartItem2);
        savedProduct.getOrderItems().add(orderItem1);
        savedProduct.getOrderItems().add(orderItem2);

        Long savedProductId = savedProduct.getId();
        Long savedGroupId = savedGroup.getId();

        List<CartItem> cartItems  = cartItemRepository.findAll();
        Long savedCartItems1Id = cartItems.get(cartItems.size() - 2).getId();
        Long savedCartItems2Id = cartItems.get(cartItems.size() - 1).getId();

        List<OrderItem> orderItems  = orderItemRepository.findAll();
        Long savedOrderItems1Id = orderItems.get(orderItems.size() - 2).getId();
        Long savedOrderItems2Id = orderItems.get(orderItems.size() - 1).getId();

        //Then
        assertTrue(productRepository.existsById(savedProductId));
        assertTrue(cartItemRepository.existsById(savedCartItems1Id));
        assertTrue(cartItemRepository.existsById(savedCartItems2Id));
        assertTrue(orderItemRepository.existsById(savedOrderItems1Id));
        assertTrue(orderItemRepository.existsById(savedOrderItems2Id));
        assertTrue(groupRepository.existsById(savedGroupId));
        assertFalse(groupRepository.findById(savedGroupId).get().getProducts().isEmpty());
        assertEquals("TestProduct",groupRepository.findById(savedGroupId).get().getProducts().iterator().next().getName());
        //ClenUp
        productRepository.deleteById(savedProduct.getId());
        cartItemRepository.deleteById(savedCartItems1Id);
        cartItemRepository.deleteById(savedCartItems2Id);
        orderItemRepository.deleteById(savedOrderItems1Id);
        orderItemRepository.deleteById(savedOrderItems2Id);
        groupRepository.deleteById(savedGroupId);
    }


    @Test
    public void shouldReadProduct(){
        //Given
        Group savedGroup = groupRepository.save(group);
        Product savedProduct = productRepository.save(product);

        savedGroup.getProducts().add(product);

        savedProduct.getCartItems().add(cartItem1);
        savedProduct.getCartItems().add(cartItem2);
        savedProduct.getOrderItems().add(orderItem1);
        savedProduct.getOrderItems().add(orderItem2);

        Long savedProductId = savedProduct.getId();
        Long savedGroupId = savedGroup.getId();

        List<CartItem> cartItems  = cartItemRepository.findAll();
        Long savedCartItems1Id = cartItems.get(cartItems.size() - 2).getId();
        Long savedCartItems2Id = cartItems.get(cartItems.size() - 1).getId();

        List<OrderItem> orderItems  = orderItemRepository.findAll();
        Long savedOrderItems1Id = orderItems.get(orderItems.size() - 2).getId();
        Long savedOrderItems2Id = orderItems.get(orderItems.size() - 1).getId();
        //When
        Group readGroup = productRepository.findById(savedProductId).get().getGroup();
        Optional<Product> readProduct = productRepository.findById(savedProductId);
        CartItem readCartItem1 = productRepository.findById(savedProductId).get().getCartItems().get(cartItems.size()-2);
        CartItem readCartItem2 = productRepository.findById(savedProductId).get().getCartItems().get(cartItems.size()-1);
        OrderItem readOrderItem1 = productRepository.findById(savedProductId).get().getOrderItems().get(orderItems.size()-2);
        OrderItem readOrderItem2 = productRepository.findById(savedProductId).get().getOrderItems().get(orderItems.size()-1);

        //Then
        assertTrue(readProduct.isPresent());
        assertEquals("TestGroup",readGroup.getName());
        assertEquals("TestProduct",readProduct.get().getName());
        assertEquals("Test",readProduct.get().getDescription());
        assertEquals(new BigDecimal("24.99"),readProduct.get().getPrice());
        assertEquals(10,readCartItem1.getQuantity());
        assertEquals(8,readCartItem2.getQuantity());
        assertEquals(10,readOrderItem1.getQuantity());
        assertEquals(8,readOrderItem2.getQuantity());

        //ClenUp
        productRepository.deleteById(savedProduct.getId());
        cartItemRepository.deleteById(savedCartItems1Id);
        cartItemRepository.deleteById(savedCartItems2Id);
        orderItemRepository.deleteById(savedOrderItems1Id);
        orderItemRepository.deleteById(savedOrderItems2Id);
        groupRepository.deleteById(savedGroupId);
    }

    @Test
    public void shouldDeleteProduct(){
        //Given
        Group savedGroup = groupRepository.save(group);
        Product savedProduct = productRepository.save(product);

        savedGroup.getProducts().add(product);

        savedProduct.getCartItems().add(cartItem1);
        savedProduct.getCartItems().add(cartItem2);
        savedProduct.getOrderItems().add(orderItem1);
        savedProduct.getOrderItems().add(orderItem2);

        Long savedProductId = savedProduct.getId();
        Long savedGroupId = savedGroup.getId();

        List<CartItem> cartItems  = cartItemRepository.findAll();
        Long savedCartItems1Id = cartItems.get(cartItems.size() - 2).getId();
        Long savedCartItems2Id = cartItems.get(cartItems.size() - 1).getId();

        List<OrderItem> orderItems  = orderItemRepository.findAll();
        Long savedOrderItems1Id = orderItems.get(orderItems.size() - 2).getId();
        Long savedOrderItems2Id = orderItems.get(orderItems.size() - 1).getId();

        int groupProductsSize = savedGroup.getProducts().size();
        //When


        cartItemRepository.deleteById(savedCartItems1Id);
        cartItemRepository.deleteById(savedCartItems2Id);
        orderItemRepository.deleteById(savedOrderItems1Id);
        orderItemRepository.deleteById(savedOrderItems2Id);
        productRepository.deleteById(savedProductId);
        groupRepository.findById(savedGroupId).get().getProducts().remove(groupProductsSize-1);

        //Then
        assertFalse(productRepository.existsById(savedProductId));
        assertEquals(0, groupRepository.findById(savedGroupId).get().getProducts().size());
        assertFalse(cartItemRepository.existsById(savedCartItems1Id));
        assertFalse(cartItemRepository.existsById(savedCartItems2Id));
        assertFalse(orderItemRepository.existsById(savedOrderItems1Id));
        assertFalse(orderItemRepository.existsById(savedOrderItems2Id));
    }

}
