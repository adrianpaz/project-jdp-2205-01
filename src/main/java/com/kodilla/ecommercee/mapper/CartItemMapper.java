package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.dto.CartItemDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemMapper {

    private CartService cartService;
    private ProductService productService;

    public CartItem mapToCartItem(CartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {
        return new CartItem(
                cartItemDto.getId(),
                cartService.getCart(cartItemDto.getCart().getId()),
                productService.getProduct(cartItemDto.getProduct().getId()),
                cartItemDto.getQuantity()
        );
    }

    public CartItemDto mapToCartItemDto(CartItem cartItem) throws CartNotFoundException, ProductNotFoundException {
        return new CartItemDto(
                cartItem.getId(),
                cartService.getCart(cartItem.getCart().getId()),
                productService.getProduct(cartItem.getProduct().getId()),
                cartItem.getQuantity()
        );
    }

    public List<CartItem> mapToCartItemList(List<CartItemDto> cartItemDtoList) {
        List<CartItem> collect = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            CartItem cartItem = null;
            try {
                cartItem = mapToCartItem(cartItemDto);
            } catch (CartNotFoundException e) {
                e.printStackTrace();
            } catch (ProductNotFoundException e) {
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
            } catch (CartNotFoundException e) {
                e.printStackTrace();
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(cartItemDto);
        }
        return collect;
    }
}
