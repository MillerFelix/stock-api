package com.kaspperacademy.stockapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

//    Tava criando uma req infinita, para eu trazer essa lista é melhor fazer uma query e buscar diretamente a lista.

//    @OneToMany(mappedBy = "type")
//    private List<Product> products;
}
