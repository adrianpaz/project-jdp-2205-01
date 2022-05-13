package com.kodilla.ecommercee.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
}