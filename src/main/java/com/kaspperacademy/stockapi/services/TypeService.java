package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.TypeDto;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.repositories.ProductRepository;
import com.kaspperacademy.stockapi.repositories.TypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Type> listTypes() {
        return typeRepository.findAll();
    }

    public Type getType(Long id) {
        Optional<Type> type = typeRepository.findById(id);
        return type.orElseThrow();
    }

    @Transactional
    public Type save(TypeDto dto) {
        validateTypeDto(dto);
        Type type = convertToType(dto);
        return typeRepository.save(type);
    }

    public void update(Long id, TypeDto dto) {
        validateTypeDto(dto);
        Type type = typeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Type not found."));
        BeanUtils.copyProperties(dto, type);
        typeRepository.save(type);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Type ID cannot be null.");
        }
        Type type = typeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Type not found for ID: " + id));
        if (productRepository.existsById(id)) {
            throw new IllegalArgumentException("The type is being used in one or more products and cannot be deleted.");
        }
        typeRepository.deleteById(id);
    }

    private Type convertToType(TypeDto dto) {
        Type type = new Type();
        type.setName(dto.getName());
        return type;
    }

    private void validateTypeDto(TypeDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("The DTO object cannot be null.");
        }
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("The 'name' field in the DTO cannot be empty.");
        }
    }
}
