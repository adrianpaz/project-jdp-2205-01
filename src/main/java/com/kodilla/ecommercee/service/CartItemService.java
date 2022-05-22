package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.exception.CartItemNotFoundException;
import com.kodilla.ecommercee.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem getCartItem(Long id) throws CartItemNotFoundException {
        return  cartItemRepository.findById(id).orElseThrow(CartItemNotFoundException::new);
    }
    public void deleteCartItem(Long cartItemId){cartItemRepository.deleteById(cartItemId);}

    public void deleteCartItemList(List<CartItem> cartItems){
        for(CartItem cartItem: cartItems){
            cartItemRepository.deleteById(cartItem.getId());
        }
    }


}
