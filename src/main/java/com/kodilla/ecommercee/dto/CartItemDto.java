package com.kodilla.ecommercee.dto;

import java.math.BigDecimal;

public class CartItemDto {

    private Long id;
    private ProductDto productDto;
    private int quantity;
    private BigDecimal cost;
}
