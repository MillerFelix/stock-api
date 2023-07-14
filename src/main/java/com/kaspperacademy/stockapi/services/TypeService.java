package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.TypeDto;
import com.kaspperacademy.stockapi.models.Type;
import com.kaspperacademy.stockapi.repositories.TypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public List<Type> listTypes() {
        return typeRepository.findAll();
    }

    public Type getType(Long id) {
        Optional<Type> type = typeRepository.findById(id);
        return type.orElseThrow();
    }

    @Transactional
    public Type save(TypeDto dto) {
        Type type = convertToType(dto);
        return typeRepository.save(type);
    }

    public void update(Long id, TypeDto dto) {
        Type type = typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found"));
        BeanUtils.copyProperties(dto, type);
        typeRepository.save(type);
    }

//    Erro ao deletar tipo, por estar sendo usado em algum produto.
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    private Type convertToType(TypeDto dto) {
        Type type = new Type();
        type.setName(dto.getName());
        return type;
    }
}
