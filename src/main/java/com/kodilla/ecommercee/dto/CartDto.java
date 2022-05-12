package com.kodilla.ecommercee.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private Long userId;
    private List<CartItemDto> cartItems;
}