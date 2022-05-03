package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping
    public void createCart(CartDto cartDto) {
    }

    @GetMapping(value = "{cartId}")
    public List<ProductDto> getProductsFromCart(@PathVariable Long cartId) {
        return new ArrayList<>();
    }

    @PutMapping
    public CartDto addProductsToCart(Long cartId, List<ProductDto> productsDto) {
        return new CartDto();
    }

    @DeleteMapping(value = "{productId}")
    public void deleteProductFromCart(Long cartId, @PathVariable Long productId) {
    }

    @PostMapping(value = "{cartId}")
    public void createOrder(Long userId, @PathVariable Long cartId) {
    }
}
