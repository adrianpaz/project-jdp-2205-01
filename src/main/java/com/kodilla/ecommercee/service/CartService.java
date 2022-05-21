package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart getCart(final Long id) throws CartNotFoundException{
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }
    public Cart saveCart(final Cart cart){ return cartRepository.save(cart);}

    public void addCartItem(final Long id, final CartItem cartItem){
        cartRepository.findById(id).get().getCartItems().add(cartItem);
    }

    public void removeCartItem(final Long cartId, final Long cartItemId){
        cartRepository.findById(cartId).get().getCartItems()
                .removeIf(cartItem -> cartItem.getId().longValue() == cartItemId.longValue());
    }

    public void removeAllCartItems(final Long id){
        cartRepository.findById(id).get().getCartItems().clear();
    }

    public Order createOrderByCart(final User user,final List<CartItem> cartItems){
        Order order = new Order(user);
        ArrayList<CartItem> cartItemArrayList = (ArrayList<CartItem>) cartItems;
        for(CartItem cartItem: cartItemArrayList){
            order.getOrderItems().add(new OrderItem(order,cartItem.getProduct(),cartItem.getQuantity()));
        }
        return order;
    }
}
