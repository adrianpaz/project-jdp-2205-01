package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = "product",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(
            targetEntity = OrderItem.class,
            mappedBy = "product",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Product(String name, String description, BigDecimal price, Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }
}