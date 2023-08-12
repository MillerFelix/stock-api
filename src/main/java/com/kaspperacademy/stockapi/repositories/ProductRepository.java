package com.kaspperacademy.stockapi.repositories;

import com.kaspperacademy.stockapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.type.id = :typeId")
    List<Product> findByTypeId(@Param("typeId") Long typeId);

    @Query("SELECT p.type.name, SUM(p.amount) FROM Product p GROUP BY p.type.id")
    List<Object[]> findAmountByType();

    @Query("SELECT p.type.name, SUM(p.value * p.amount) FROM Product p GROUP BY p.type.id")
    List<Object[]> findValuesByType();

    @Query("SELECT p.name, SUM(p.value * p.amount) FROM Product p GROUP BY p.id")
    List<Object[]> findValuesByProducts();

    @Query("SELECT SUM(p.value * p.amount) FROM Product p WHERE p.id = :id")
    BigDecimal findValueByProduct(@Param("id") Long id);

}
