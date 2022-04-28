package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    public List<> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping()
    public String getProduct() {
        return "Product";
    }

    @PostMapping()
    public String createProduct() {
        return "Product has been created";
    }

    @PutMapping()
    public String updateProduct() {
        return "Product has benn updated";
    }

    @DeleteMapping()
    public String deleteProduct() {
        return "Product has been deleted";
    }
}
