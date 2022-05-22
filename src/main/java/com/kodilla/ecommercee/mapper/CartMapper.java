package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public Cart mapToCart(CartDto cartDto){
        return new Cart(
                cartDto.getUser()
        );
    }

    public CartDto mapToCartDto(Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser(),
                cartItemMapper.mapToCartItemDtoList(cart.getCartItems())
                );
    }

}
