package com.kaspperacademy.stockapi.controllers;

import com.kaspperacademy.stockapi.dto.SupplierDto;
import com.kaspperacademy.stockapi.models.Supplier;
import com.kaspperacademy.stockapi.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@Validated
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<Supplier> listSuppliers() {
        return supplierService.listSuppliers();
    }

    @GetMapping("/suppliers")
    public Page<Supplier> paginatedListSupplier(Pageable pageable) {
        return supplierService.paginatedListSupplier(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable Long id) {
        try {
            Supplier supplier = supplierService.getSupplier(id);
            return ResponseEntity.ok().body(supplier);
        } catch (RuntimeException e) {
            String errorMessage = "Supplier not found for ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SupplierDto dto) {
        try {
            Supplier supplier = supplierService.save(dto);
            return ResponseEntity.ok().body("Supplier saved successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error saving supplier: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody SupplierDto dto) {
        try {
            supplierService.update(id, dto);
            return ResponseEntity.ok("Supplier updated successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error when updating the supplier: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            supplierService.delete(id);
            return ResponseEntity.ok("Supplier removed successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Request error: " + e.getMessage());
        }
    }

}