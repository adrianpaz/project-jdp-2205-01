package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private Long id;
    private String username;
    private String status;
    private Long userKey;

}
