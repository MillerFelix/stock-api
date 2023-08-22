package com.kaspperacademy.stockapi.repositories;

import com.kaspperacademy.stockapi.dto.FilterCategoriesSuppliersDto;
import com.kaspperacademy.stockapi.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT DISTINCT s.category FROM Supplier s")
    List<String> findAllCategories();

    @Query("SELECT s FROM Supplier s WHERE (:category IS NULL OR s.category = :category)")
    Page<Supplier> findByCategory(String category, Pageable pageable);

    @Query("SELECT s.state, COUNT(s) FROM Supplier s GROUP BY s.state")
    List<Object[]> findSuppliersByState();

    @Query("SELECT s.category, COUNT(s) FROM Supplier s GROUP BY s.category")
    List<Object[]> findSuppliersByCategory();

    @Query("SELECT p.supplier.name, SUM(p.amount) FROM Product p GROUP BY p.supplier.name")
    List<Object[]> findProductsBySupplier();

}
