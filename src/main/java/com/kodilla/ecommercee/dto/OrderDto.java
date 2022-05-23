package com.kodilla.ecommercee.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private List<OrderItemDto> orderItems;
}
