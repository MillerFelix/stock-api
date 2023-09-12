package com.kaspperacademy.stockapi.controllers;

import com.kaspperacademy.stockapi.dto.FilterTypeProductsDto;
import com.kaspperacademy.stockapi.dto.TypeDto;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.springframework.http.RequestEntity.post;

@RestController
@RequestMapping("/types")
@Validated
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public List<Type> listTypes() {
        return typeService.listTypes();
    }

    @GetMapping("/types")
    public List<Type> paginatedListType() {
        return typeService.paginatedListType();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getType(@PathVariable Long id) {
        try {
            List<FilterTypeProductsDto> typesProducts = typeService.getTypesProducts(id);
            return ResponseEntity.ok().body(typesProducts);
        } catch (RuntimeException e) {
            String errorMessage = "Type not found for ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TypeDto dto) {
        try {
            Type type = typeService.save(dto);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                post("message", "Type saved successfully!");
            }});
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error saving product: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TypeDto dto) {
        try {
            typeService.update(id, dto);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "Type updated successfully!");
            }});
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error when updating the type: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        typeService.delete(id);
        return ResponseEntity.ok("Type removed successfully!");
    }

}
