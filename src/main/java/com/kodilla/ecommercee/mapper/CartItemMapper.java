package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.dto.CartItemDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemMapper {

    private final CartService cartService;
    private final ProductService productService;

    public CartItem mapToCartItem(CartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {
        return new CartItem(
                cartItemDto.getId(),
                cartService.getCart(cartItemDto.getCartId()),
                productService.getProduct(cartItemDto.getProductId()),
                cartItemDto.getQuantity()
        );
    }

    public CartItemDto mapToCartItemDto(CartItem cartItem) throws CartNotFoundException, ProductNotFoundException {
        return new CartItemDto(
                cartItem.getId(),
                cartItem.getCart().getId(),
                cartItem.getProduct().getId(),
                cartItem.getQuantity()
        );
    }

    public List<CartItem> mapToCartItemList(List<CartItemDto> cartItemDtoList) {
        List<CartItem> collect = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            CartItem cartItem = null;
            try {
                cartItem = mapToCartItem(cartItemDto);
            } catch (CartNotFoundException | ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(cartItem);
        }
        return collect;
    }

    public List<CartItemDto> mapToCartItemDtoList(List<CartItem> cartItemList) {
        List<CartItemDto> collect = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            CartItemDto cartItemDto = null;
            try {
                cartItemDto = mapToCartItemDto(cartItem);
            } catch (CartNotFoundException | ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(cartItemDto);
        }
        return collect;
    }
}
