package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CartItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getItemsFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(new CartDto());
    }

    @PutMapping(value = "{cartId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long cartId, @RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok(new CartDto());
    }

    @DeleteMapping(value = "{cartId}/{cartItemId}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "{cartId}")
    public ResponseEntity<Void> createOrder(@PathVariable Long cartId) {
        return ResponseEntity.ok().build();
    }
}
