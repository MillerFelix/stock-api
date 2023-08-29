package com.kaspperacademy.stockapi.controllers;


import com.kaspperacademy.stockapi.dto.*;
import com.kaspperacademy.stockapi.models.Product;
import com.kaspperacademy.stockapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<FilterProductsDto>> listProducts() {
        List<FilterProductsDto> filterProducts = productService.listProducts();
        return ResponseEntity.ok(filterProducts);
    }

    @GetMapping("/products")
    public List<Product> paginatedListProduct() {
        return productService.paginatedListProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            List<Product> products = productService.getProduct(id);
            return ResponseEntity.ok().body(products);
        } catch (RuntimeException e) {
            String errorMessage = "Product not found for ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<?> getProductsByTypeId(@PathVariable Long id) {
        List<Product> products = productService.getProductsByTypeId(id);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDto dto) {
        try {
            Product product = productService.save(dto);
            return ResponseEntity.ok().body("Product saved successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error saving product: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        try {
            productService.update(id, dto);
            return ResponseEntity.ok("Product updated successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error when updating the product: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            productService.delete(id);
            return ResponseEntity.ok("Product removed successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Request error: " + e.getMessage());
        }
    }

    @GetMapping("/types-amount")
    public ResponseEntity<List<GraphTypeAmountDto>> getAmountByType() {
        try {
            List<GraphTypeAmountDto> data = productService.getAmountByType();
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/types-values")
    public ResponseEntity<List<GraphTypeValuesDto>> getValuesByType() {
        try {
            List<GraphTypeValuesDto> data = productService.getValuesByType();
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/products-values")
    public ResponseEntity<List<GraphProductValuesDto>> getProductsValues() {
        try {
            List<GraphProductValuesDto> data = productService.getValuesByProducts();
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/product-value/{id}")
    public ResponseEntity<BigDecimal> findProductValue(@PathVariable Long id) {
        try {
            BigDecimal value = productService.findValueByProduct(id);
            return ResponseEntity.ok(value);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}