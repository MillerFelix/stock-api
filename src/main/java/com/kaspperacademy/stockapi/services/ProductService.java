package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.ProductDto;
import com.kaspperacademy.stockapi.models.Product;
import com.kaspperacademy.stockapi.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow();
    }

    @Transactional
    public Product save(ProductDto dto) {
        Product product = convertToProduct(dto);
        return productRepository.save(product);
    }

    public void update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        BeanUtils.copyProperties(dto, product);
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private Product convertToProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setType(dto.getType());
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        product.setDescription(dto.getDescription());
        return product;
    }

}
