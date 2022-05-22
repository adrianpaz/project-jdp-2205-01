package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private List<OrderItemDto> orderItems;
}
