package com.kodilla.ecommercee.domain;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class Token {

    private final Long key;
    private final LocalTime creationDate;
    private final LocalTime expirationDate;

    public Token(Long key, LocalTime creationDate, LocalTime expirationDate) {
        this.key = key;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }
}
