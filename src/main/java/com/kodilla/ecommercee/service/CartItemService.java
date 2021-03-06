package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.CartItemNotFoundException;
import com.kodilla.ecommercee.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem getCartItem(final Long id) throws CartItemNotFoundException {
        return  cartItemRepository.findById(id).orElseThrow(CartItemNotFoundException::new);
    }
    public void deleteCartItem(final Long cartItemId){cartItemRepository.deleteById(cartItemId);}

    public CartItem save(final CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItemList(final List<CartItem> cartItems){
        for(CartItem cartItem: cartItems){
            cartItemRepository.deleteById(cartItem.getId());
        }
    }

    public List<Product> cartItemsToProduct(List<CartItem> cartItems){
        return  cartItems.stream().map(CartItem::getProduct)
                .collect(Collectors.toList());
    }
}
