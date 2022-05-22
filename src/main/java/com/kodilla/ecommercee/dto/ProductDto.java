package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Group;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;
    private List<CartItemDto> cartItems;
    private List<OrderItemDto> orderItems;
}