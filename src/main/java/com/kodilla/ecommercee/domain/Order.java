package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public final class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;

    private Long userId;

    private List<CartItemDto>


}
