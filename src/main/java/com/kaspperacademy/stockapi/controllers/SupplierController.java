package com.kaspperacademy.stockapi.controllers;

import com.kaspperacademy.stockapi.dto.*;
import com.kaspperacademy.stockapi.models.Supplier;
import com.kaspperacademy.stockapi.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.springframework.http.RequestEntity.post;

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
    public List<Supplier> paginatedListSupplier() {
        return supplierService.paginatedListSupplier();
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable Long id) {
        try {
            List<Supplier> suppliers = supplierService.getSupplier(id);
            return ResponseEntity.ok().body(suppliers);
        } catch (RuntimeException e) {
            String errorMessage = "Supplier not found for ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<FilterCategoriesSuppliersDto>> listCategories() {
        List<FilterCategoriesSuppliersDto> categories = supplierService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Supplier>> getSuppliersByCategory(@PathVariable String category) {
        List<Supplier> suppliers = supplierService.getSuppliersByCategory(category);
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SupplierDto dto) {
        try {
            Supplier supplier = supplierService.save(dto);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                post("message", "Supplier saved successfully!");
            }});
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error saving supplier: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SupplierDto dto) {
        try {
            supplierService.update(id, dto);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Supplier updated successfully!");
            }});
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

    @GetMapping("/amount-state")
    public ResponseEntity<List<GraphSupplierStatesDto>> getSuppliersByState() {
        List<GraphSupplierStatesDto> stateCountList = supplierService.getSuppliersByState();
        return ResponseEntity.ok(stateCountList);
    }

    @GetMapping("/amount-category")
    public ResponseEntity<List<GraphSupplierAmountCategoriesDto>> getAmountSuppliersByCategory() {
        List<GraphSupplierAmountCategoriesDto> categoryAmountList = supplierService.getAmountSuppliersByCategory();
        return ResponseEntity.ok(categoryAmountList);
    }

    @GetMapping("/amount-products")
    public ResponseEntity<List<GraphSupplierProductsAmountDto>> getProductsAmountBySupplier() {
        List<GraphSupplierProductsAmountDto> supplierProductAmountList = supplierService.getProductsBySupplier();
        return ResponseEntity.ok(supplierProductAmountList);
    }
}
