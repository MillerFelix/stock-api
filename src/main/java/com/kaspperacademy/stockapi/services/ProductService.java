package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.ProductDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Page<Product> paginatedListProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow();
    }

    public List<Product> getProductsByTypeId(Long typeId) {
        if (!typeRepository.existsById(typeId)) {
            throw new IllegalArgumentException("Type not found for ID: " + typeId);
        }
        List<Product> products = productRepository.findByTypeId(typeId);
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

    public Map<String, Integer> getAmountByType() {
        List<Object[]> data = productRepository.findAmountByType();
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data found.");
        }
        Map<String, Integer> amountByType = new HashMap<>();

        for (Object[] result : data) {
            String type = (String) result[0];
            Integer amount = ((Number) result[1]).intValue();
            amountByType.put(type, amount);
        }
        return amountByType;
    }

    public Map<String, BigDecimal> getValuesByType() {
        List<Object[]> data = productRepository.findValuesByType();
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data found.");
        }
        Map<String, BigDecimal> valueByTypeName = new HashMap<>();

        for (Object[] result : data) {
            String type = (String) result[0];
            BigDecimal value = (BigDecimal) result[1];
            valueByTypeName.put(type, value);
        }
        return valueByTypeName;
    }

    public Map<String, BigDecimal> getValuesByProducts() {
        List<Object[]> data = productRepository.findValuesByProducts();
        Map<String, BigDecimal> valuesByProduct = new HashMap<>();
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data found.");
        }

        for (Object[] result : data) {
            String productName = (String) result[0];
            BigDecimal value = (BigDecimal) result[1];
            valuesByProduct.put(productName, value);
        }
        return valuesByProduct;
    }

    public BigDecimal getValueByProduct(Long id) {
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
