package com.kodilla.ecommercee.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String status;
    private Long userKey;

}
