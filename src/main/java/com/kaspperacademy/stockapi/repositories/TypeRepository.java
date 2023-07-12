package com.kaspperacademy.stockapi.repositories;

import com.kaspperacademy.stockapi.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
