package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.TypeDto;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    public Type save(TypeDto dto) {
        Type type = convertToType(dto);
        return typeRepository.save(type);
    }

    private Type convertToType(TypeDto dto) {
        Type type = new Type();
        type.setName(dto.getName());
        return type;
    }
}
