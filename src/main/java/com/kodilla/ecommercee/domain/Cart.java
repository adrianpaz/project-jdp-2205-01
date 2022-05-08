package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;
}
