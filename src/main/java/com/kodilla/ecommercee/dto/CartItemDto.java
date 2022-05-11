package com.kodilla.ecommercee.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private ProductDto productDto;
    private CartDto cartDto;
    private int quantity;
    private BigDecimal cost;
}