package com.kodilla.ecommercee.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private Long id;
    private List<ItemDto> items;
    private BigDecimal totalCost;
}
