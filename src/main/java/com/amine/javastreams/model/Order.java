package com.amine.javastreams.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_product_relationship",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
    )
    //@ToString.Exclude
    Set<Product> products = new HashSet<>();

    //@Temporal(TemporalType.TIMESTAMP)
    /*@Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;*/

    //@Temporal(TemporalType.TIMESTAMP)
    /*@Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;*/

    public Order(LocalDate orderDate, LocalDate deliveryDate, String status, Customer customer) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
    }
}