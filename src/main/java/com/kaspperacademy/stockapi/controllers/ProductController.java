package com.kaspperacademy.stockapi.controllers;


import com.kaspperacademy.stockapi.dto.ProductDto;
import com.kaspperacademy.stockapi.models.Product;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/type/{id}")
    public List<Product> getProductsByTypeId(@PathVariable Long id) {
        return productService.getProductsByTypeId(id);
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto dto) {
        Product product = productService.save(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        productService.update(id, dto);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product removed successfully!");
    }

}
