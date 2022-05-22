package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException, GroupNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProduct(productId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(savedProduct));
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}