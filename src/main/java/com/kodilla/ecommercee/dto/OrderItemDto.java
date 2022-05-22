package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
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