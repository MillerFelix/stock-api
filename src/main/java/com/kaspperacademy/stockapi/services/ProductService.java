package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.*;
import com.kaspperacademy.stockapi.models.Product;
import com.kaspperacademy.stockapi.repositories.ProductRepository;
import com.kaspperacademy.stockapi.repositories.TypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;

    public List<FilterProductsDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<FilterProductsDto> filterProducts = new ArrayList<>();

        for (Product product : products) {
            FilterProductsDto dto = new FilterProductsDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setValue(product.getValue());
            dto.setAmount(product.getAmount());
            filterProducts.add(dto);
        }

        return filterProducts;
    }

    public List<Product> paginatedListProduct() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow();
    }

    public Page<Product> getProductsByTypeId(Long typeId, Pageable pageable) {
        if (!typeRepository.existsById(typeId)) {
            throw new IllegalArgumentException("Type not found for ID: " + typeId);
        }
        Page<Product> products = productRepository.findByTypeId(typeId, pageable);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No products were found for the type with the ID: " + typeId);
        }
        return products;
    }

    @Transactional
    public Product save(ProductDto dto) {
        validateProductDto(dto);
        Product product = convertToProduct(dto);
        return productRepository.save(product);
    }

    public void update(Long id, ProductDto dto) {
        validateProductDto(dto);
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found."));
        BeanUtils.copyProperties(dto, product);
        productRepository.save(product);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null.");
        }
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found for ID: " + id);
        }
        productRepository.deleteById(id);
    }

    public List<GraphTypeAmountDto> getAmountByType() {
        List<Object[]> data = productRepository.findAmountByType();
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data found.");
        }
        List<GraphTypeAmountDto> amountByTypeList = new ArrayList<>();

        for (Object[] result : data) {
            String typeName = (String) result[0];
            Integer amount = ((Number) result[1]).intValue();
            GraphTypeAmountDto dto = new GraphTypeAmountDto(typeName, amount);
            amountByTypeList.add(dto);
        }
        return amountByTypeList;
    }

    public List<GraphTypeValuesDto> getValuesByType() {
        List<Object[]> data = productRepository.findValuesByType();
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data found.");
        }
        List<GraphTypeValuesDto> valueByTypeList = new ArrayList<>();

        for (Object[] result : data) {
            String typeName = (String) result[0];
            BigDecimal value = (BigDecimal) result[1];
            GraphTypeValuesDto dto = new GraphTypeValuesDto(typeName, value);
            valueByTypeList.add(dto);
        }
        return valueByTypeList;
    }

    public List<GraphProductValuesDto> getValuesByProducts() {
        List<Object[]> data = productRepository.findValuesByProducts();
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data found.");
        }
        List<GraphProductValuesDto> valuesByProductList = new ArrayList<>();

        for (Object[] result : data) {
            String productName = (String) result[0];
            BigDecimal value = (BigDecimal) result[1];
            GraphProductValuesDto dto = new GraphProductValuesDto(productName, value);
            valuesByProductList.add(dto);
        }
        return valuesByProductList;
    }

    public BigDecimal findValueByProduct(Long id) {
        return productRepository.findValueByProduct(id);
    }

    private Product convertToProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setType(dto.getType());
        product.setValue(dto.getValue());
        product.setSupplier(dto.getSupplier());
        product.setAmount(dto.getAmount());
        product.setDescription(dto.getDescription());
        return product;
    }

    private void validateProductDto(ProductDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("The DTO object cannot be null.");
        }
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("The 'name' field in the DTO cannot be empty.");
        }
        if (dto.getType() == null) {
            throw new IllegalArgumentException("The 'type' field in the DTO cannot be null.");
        }
        if (dto.getValue() == null || dto.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The 'price' field in the DTO must be a valid value and greater than zero.");
        }
        if (dto.getAmount() <= 0) {
            throw new IllegalArgumentException("The 'amount' field in the DTO must be a valid value and greater than zero.");
        }
        if (dto.getDescription() == null || dto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("The 'description' field in the DTO cannot be null.");
        }
    }
}
