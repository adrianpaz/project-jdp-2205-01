package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;
    private final UserService userService;
    public Cart mapToCart(CartDto cartDto) throws UserNotFoundException {
        return new Cart(
                cartDto.getId(),
                userService.getUser(cartDto.getUserId()),
                cartItemMapper.mapToCartItemList(cartDto.getCartItems())
        );
    }

    public CartDto mapToCartDto(Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cartItemMapper.mapToCartItemDtoList(cart.getCartItems())
                );
    }

}
