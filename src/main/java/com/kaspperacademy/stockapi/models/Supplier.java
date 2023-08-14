package com.kaspperacademy.stockapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 60)
    private String contact;

    @Column(length = 60)
    private String email;

    @Column(nullable = false, length = 30)
    private String country;

    @Column(nullable = false, length = 60)
    private String state;

    @Column(length = 100)
    private String adress;

    @Column(length = 30)
    private String category;

    @Column(length = 300)
    private String comments;
}
