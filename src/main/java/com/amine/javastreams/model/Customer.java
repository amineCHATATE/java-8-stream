package com.amine.javastreams.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:22
 * @description
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer tier;

    public Customer(String name, Integer tier) {
        this.name = name;
        this.tier = tier;
    }
}