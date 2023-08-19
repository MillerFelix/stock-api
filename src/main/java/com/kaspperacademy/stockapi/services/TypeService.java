package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.FilterTypeProductsDto;
import com.kaspperacademy.stockapi.dto.ProductDto;
import com.kaspperacademy.stockapi.dto.TypeDto;
import com.kaspperacademy.stockapi.models.Product;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.repositories.ProductRepository;
import com.kaspperacademy.stockapi.repositories.TypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Type> listTypes() {
        return typeRepository.findAll();
    }

    public Page<Type> paginatedListType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    public FilterTypeProductsDto getTypesProducts(Long id) {
        Type type = typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found for ID: " + id));
        List<Product> products = typeRepository.findByType(type);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setAmount(product.getAmount());
            productDto.setValue(product.getValue());
            productDto.setDescription(product.getDescription());
            productDtos.add(productDto);
        }

        FilterTypeProductsDto typeDto = new FilterTypeProductsDto();
        typeDto.setId(type.getId());
        typeDto.setName(type.getName());
        typeDto.setDescription(type.getDescription());
        typeDto.setProducts(productDtos);
        return typeDto;
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
        type.setDescription(dto.getDescription());
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
