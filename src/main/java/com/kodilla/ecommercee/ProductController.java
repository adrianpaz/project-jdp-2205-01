package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductController {
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    public ProductDto getProduct() {
        return new ProductDto();
    }

    public void createProduct() {

    }

    public ProductDto updateProduct() {
        return new ProductDto();
    }

    public void deleteProduct() {

    }
}
