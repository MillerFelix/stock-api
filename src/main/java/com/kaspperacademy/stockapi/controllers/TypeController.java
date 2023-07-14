package com.kaspperacademy.stockapi.controllers;

import com.kaspperacademy.stockapi.dto.TypeDto;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@Validated
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public List<Type> listProducts() {
        return typeService.listTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getProduct(@PathVariable Long id) {
        Type type = typeService.getType(id);
        return ResponseEntity.ok().body(type);
    }

    @PostMapping
    public ResponseEntity<TypeDto> save(@RequestBody TypeDto dto) {
        Type type = typeService.save(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody TypeDto dto) {
        typeService.update(id, dto);
        return ResponseEntity.ok("Type updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        typeService.delete(id);
        return ResponseEntity.ok("Type removed successfully!");
    }

}
