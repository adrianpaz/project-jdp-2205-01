package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartItemTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    public void createCartItem() {
        //Given
        CartItem cartItem = new CartItem(null, null, 1);

        //When
        CartItem saveCartItem = cartItemRepository.save(cartItem);
        Long saveCartItemId = saveCartItem.getId();

        //Then
        Assert.assertTrue(cartItemRepository.existsById(saveCartItemId));

        //Clean
        cartItemRepository.deleteById(saveCartItemId);
    }

    @Test
    public void readCartItem() {
        //Given
        CartItem cartItem = new CartItem(null, null, 1);

        //When
        CartItem saveCartItem = cartItemRepository.save(cartItem);
        Long saveCartItemId = saveCartItem.getId();

        Optional<CartItem> readCartItem = cartItemRepository.findById(saveCartItemId);

        //Then
        Assert.assertTrue(readCartItem.isPresent());

        //Clean
        cartItemRepository.deleteById(saveCartItemId);
    }

    @Test
    public void deleteCartItem() {
        //Given
        CartItem cartItem = new CartItem(null, null, 1);

        //When
        CartItem saveCartItem = cartItemRepository.save(cartItem);
        Long saveCartItemId = saveCartItem.getId();

        cartItemRepository.deleteById(saveCartItemId);

        //Then
        Assert.assertFalse(cartItemRepository.existsById(saveCartItemId));
    }
}
