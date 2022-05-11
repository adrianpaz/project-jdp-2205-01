package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.User;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private User user;
    private List<CartItem> cartItems;
}