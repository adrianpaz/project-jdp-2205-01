package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}