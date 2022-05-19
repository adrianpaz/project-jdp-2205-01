package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    public void createCart() {
        //Given
        Cart cart = new Cart(null);
        CartItem cartItem = new CartItem(cart, null, 1);

        //When
        Cart saveCart = cartRepository.save(cart);
        saveCart.getCartItems().add(cartItem);
        Long saveCardId = saveCart.getId();
        CartItem saveCartItemId = cartItemRepository.save(cartItem);

        //Then
        Assert.assertTrue(cartItemRepository.existsById(saveCartItemId));
        Assert.assertTrue(cartRepository.existsById(saveCardId));
        Assert.assertTrue(cartItemRepository.existsById(saveCartItemId));

        //Clean
        cartItemRepository.deleteById(saveCardId);
        cartRepository.deleteById(saveCardId);
    }

    @Test
    public void readCart() {
        //Given
        Cart cart = new Cart(null);
        CartItem cartItem = new CartItem(cart, null, 1);

        //When
        cart.getCartItems().add(cartItem);
        Cart saveCart = cartRepository.save(cart);
        Long saveCartId = saveCart.getId();

        Optional<Cart> readCart = cartRepository.findById(saveCartId);

        //Then
        Assert.assertTrue(readCart.isPresent());

        //Clean
        cartRepository.deleteById(saveCartId);
    }

    @Test
    public void deleteCart() {
        //Given
        Cart cart = new Cart(null);

        //When
        cartRepository.save(cart);
        cartRepository.deleteById(cart.getId());

        //Then
        Assert.assertFalse(cartRepository.existsById(cart.getId()));
    }
}