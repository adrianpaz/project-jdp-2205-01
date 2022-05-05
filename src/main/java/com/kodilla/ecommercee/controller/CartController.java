package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ItemDto>> getItemsFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping
    public ResponseEntity<CartDto> addItemToCart(Long cartId, @RequestBody ItemDto ItemDto) {
        return ResponseEntity.ok(new CartDto());
    }

    @DeleteMapping(value = "{itemId}")
    public ResponseEntity<Void> deleteProductFromCart(Long cartId, @PathVariable Long itemId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "{cartId}")
    public ResponseEntity<Void> createOrder(Long userId, @PathVariable Long cartId) {
        return ResponseEntity.ok().build();
    }
}
