package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CartItemDto;
import com.kodilla.ecommercee.exception.CartItemNotFoundException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.CartItemMapper;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.service.CartItemService;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(cart);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<CartItemDto>> getItemsFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId);
        List<CartItemDto> cartItemsDto = cartItemMapper.mapToCartItemDtoList(cart.getCartItems());
        return ResponseEntity.ok(cartItemsDto);
    }

    @PutMapping(value = "{cartId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long cartId, @RequestBody CartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {
        CartItem cartItem = cartItemMapper.mapToCartItem(cartItemDto);
        cartService.addCartItem(cartId, cartItem);
        productService.addCartItem(cartItem);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.getCart(cartId)));
    }

    @DeleteMapping(value = "{cartId}/{cartItemId}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) throws CartItemNotFoundException {
        cartService.removeCartItem(cartId,cartItemId);
        productService.removeCartItem(cartItemService.getCartItem(cartItemId).getProduct().getId(),cartItemId);
        cartItemService.deleteCartItem(cartItemId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "{cartId}")
    public ResponseEntity<Void> createOrder(@PathVariable Long cartId) throws CartNotFoundException, OrderNotFoundException {
        Cart cart = cartService.getCart(cartId);
        List<CartItem> cartItems = cart.getCartItems();
        Order order = cartService.createOrderByCart(cart.getUser(),cartItems);
        orderService.save(order);
        cartService.removeAllCartItems(cartId);
        productService.removeCartItems(cartItems);
        cartItemService.deleteCartItemList(cartItems);
        return ResponseEntity.ok().build();
    }
}