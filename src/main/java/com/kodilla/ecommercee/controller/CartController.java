package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CartItemDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.mapper.CartItemMapper;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.service.*;
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
    private final ProductMapper productMapper;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(cartMapper.mapToCart(cartDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getItemsFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        List<CartItem> cartItems = cartService.getCart(cartId).getCartItems();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(cartItemService.cartItemsToProduct(cartItems)));
    }

    @PutMapping(value = "{cartId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long cartId, @RequestBody CartItemDto cartItemDto) throws CartNotFoundException, ProductNotFoundException {
        CartItem cartItem = cartItemMapper.mapToCartItem(cartItemDto);
        cartItemService.save(cartItem);
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.getCart(cartId)));
    }

    @DeleteMapping(value = "{cartItemId}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable Long cartItemId){
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "{cartId}")
    public ResponseEntity<Void> createOrder(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Order order = cartService.createOrderByCart(cart.getUser(),cart.getCartItems());
        cartItemService.deleteCartItemList(cart.getCartItems());
        orderService.saveOrder(order);
        return ResponseEntity.ok().build();
    }
}