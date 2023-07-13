package com.kaspperacademy.stockapi.controllers;

import com.kaspperacademy.stockapi.dto.TypeDto;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/types")
@Validated
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping
    public ResponseEntity<TypeDto> save(@RequestBody TypeDto dto) {
        Type type = typeService.save(dto);
        return ResponseEntity.ok().body(dto);
    }
}
