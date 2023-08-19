package com.kaspperacademy.stockapi.repositories;

import com.kaspperacademy.stockapi.models.Product;
import com.kaspperacademy.stockapi.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("SELECT p FROM Product p WHERE p.type = ?1")
    List<Product> findByType(Type type);
}
