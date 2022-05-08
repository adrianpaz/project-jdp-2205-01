package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CART_ITEMS")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "COST")
    private BigDecimal cost;

}
