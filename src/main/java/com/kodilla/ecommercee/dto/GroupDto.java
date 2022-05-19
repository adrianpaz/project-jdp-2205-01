package com.kodilla.ecommercee.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Long id;
    private String name;
    private List<ProductDto> products;

    public GroupDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}