package com.kodilla.ecommercee.domain;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(
            targetEntity = OrderItem.class,
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<OrderItem> orderItemList;
}
