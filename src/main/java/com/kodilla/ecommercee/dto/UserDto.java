package com.kodilla.ecommercee.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String status;
    private Long userKey;
}