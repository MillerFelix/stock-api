package com.kaspperacademy.stockapi.controllers;


import com.kaspperacademy.stockapi.dto.ProductDto;
import com.kaspperacademy.stockapi.models.Product;
import com.kaspperacademy.stockapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProduct(id);
            return ResponseEntity.ok().body(product);
        } catch (RuntimeException e) {
            String errorMessage = "Product not found for ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<?> getProductsByTypeId(@PathVariable Long id) {
        List<Product> products = productService.getProductsByTypeId(id);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
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
}
