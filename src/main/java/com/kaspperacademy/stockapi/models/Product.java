package com.kaspperacademy.stockapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(length = 100)
    private String supplier;

    @Column(nullable = false)
    private Integer amount;

    @Column(length = 300)
    private String description;

}
