package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<Void> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Void> createProduct() {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }
}
