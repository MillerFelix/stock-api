package com.kaspperacademy.stockapi.repositories;

import com.kaspperacademy.stockapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.type.id = :typeId")
    List<Product> findByTypeId(@Param("typeId") Long typeId);

}
