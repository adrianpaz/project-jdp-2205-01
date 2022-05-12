package com.kodilla.ecommercee.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String status;
    private Long userKey;
    private List<OrderDto> orders;
}