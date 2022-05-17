package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
        Long saveCartId = saveCart.getId();
        CartItem saveCartItem = cartItemRepository.save(cartItem);
        Long saveCartItemId = saveCartItem.getId();

        //Then
        Assert.assertTrue(cartRepository.existsById(saveCartId));
        Assert.assertTrue(cartItemRepository.existsById(saveCartItemId));

        //Clean
        cartItemRepository.deleteById(saveCartItemId);
        cartRepository.deleteById(saveCartId);
    }

    @Test
    public void readCart() {
        //Given
        Cart cart = new Cart(null);
        CartItem cartItem = new CartItem(cart, null, 1);

        //When
        Cart saveCart = cartRepository.save(cart);
        saveCart.getCartItems().add(cartItem);
        Long saveCartId = saveCart.getId();
        CartItem saveCartItem = cartItemRepository.save(cartItem);
        Long saveCartItemId = saveCartItem.getId();

        Optional<Cart> readCart = cartRepository.findById(saveCartId);
        Optional<CartItem> readCartItem = cartItemRepository.findById(saveCartItemId);

        //Then
        Assert.assertTrue(readCart.isPresent());
        Assert.assertTrue(readCartItem.isPresent());

        //Clean
        cartItemRepository.deleteById(saveCartItemId);
        cartRepository.deleteById(saveCartId);
    }

    @Test
    public void deleteCart() {
        //Given
        Cart cart = new Cart(null);
        CartItem cartItem = new CartItem(cart, null, 1);

        //When
        Cart saveCart = cartRepository.save(cart);
        saveCart.getCartItems().add(cartItem);
        Long saveCartId = saveCart.getId();
        CartItem saveCartItem = cartItemRepository.save(cartItem);
        Long saveCartItemId = saveCartItem.getId();

        cartItemRepository.deleteById(saveCartItemId);
        cartRepository.deleteById(saveCartId);

        //Then
        Assert.assertFalse(cartItemRepository.existsById(saveCartItemId));
        Assert.assertFalse(cartRepository.existsById(saveCartId));
    }
}