package com.kaspperacademy.stockapi.repositories;

import com.kaspperacademy.stockapi.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
