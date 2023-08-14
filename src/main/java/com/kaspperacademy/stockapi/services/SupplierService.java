package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.SupplierDto;
import com.kaspperacademy.stockapi.models.Supplier;
import com.kaspperacademy.stockapi.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> listSuppliers() {
        return supplierRepository.findAll();
    }

    public Page<Supplier> paginatedListSupplier(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    public Supplier getSupplier(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.orElseThrow();
    }

    @Transactional
    public Supplier save(SupplierDto dto) {
        validateSupplierDto(dto);
        Supplier supplier = convertToSupplier(dto);
        return supplierRepository.save(supplier);
    }

    public void update(Long id, SupplierDto dto) {
        validateSupplierDto(dto);
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Supplier not found."));
        BeanUtils.copyProperties(dto, supplier);
        supplierRepository.save(supplier);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Supplier ID cannot be null.");
        }
        if (!supplierRepository.existsById(id)) {
            throw new IllegalArgumentException("Supplier not found for ID: " + id);
        }
        supplierRepository.deleteById(id);
    }

    private Supplier convertToSupplier(SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setEmail(dto.getEmail());
        supplier.setCountry(dto.getCountry());
        supplier.setState(dto.getState());
        supplier.setAdress(dto.getAdress());
        supplier.setCategory(dto.getCategory());
        supplier.setComments(dto.getComments());
        return supplier;
    }

    private void validateSupplierDto(SupplierDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("The DTO object cannot be null.");
        }
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("The 'name' field in the DTO cannot be empty.");
        }
        if (dto.getCountry() == null || dto.getCountry().isEmpty()) {
            throw new IllegalArgumentException("The 'Country' field in the DTO cannot be empty.");
        }
        if (dto.getState() == null || dto.getState().isEmpty()) {
            throw new IllegalArgumentException("The 'State' field in the DTO cannot be empty.");
        }
        if (dto.getContact() == null) {
            throw new IllegalArgumentException("The 'contact' field in the DTO cannot be null.");
        }
    }
}