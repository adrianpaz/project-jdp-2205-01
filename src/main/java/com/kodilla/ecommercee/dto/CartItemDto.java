package com.kodilla.ecommercee.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Long cartId;
    private ProductDto productDto;
    private int quantity;
}