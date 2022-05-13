package com.kodilla.ecommercee.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Long cartId;
    private Long productId;
    private int quantity;
}