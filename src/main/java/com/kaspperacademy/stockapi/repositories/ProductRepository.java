package com.kaspperacademy.stockapi.repositories;

import com.kaspperacademy.stockapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
