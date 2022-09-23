package com.amine.javastreams.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:22
 * @description
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    // With Lombok
    @With(AccessLevel.PUBLIC)
    private Double price;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    //@ToString.Exclude
    private Set<Order> orders = new HashSet<>();

    public Product(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Vanilla Java
    /* public Product withPrice(double newPrice) {
        return this.price == newPrice ? this : new Product(this.id, this.name, this.category, newPrice, this.orders);
    }*/
}