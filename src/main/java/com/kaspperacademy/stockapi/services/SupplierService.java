package com.kaspperacademy.stockapi.services;

import com.kaspperacademy.stockapi.dto.*;
import com.kaspperacademy.stockapi.models.Supplier;
import com.kaspperacademy.stockapi.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> listSuppliers() {
        return supplierRepository.findAll();
    }

    public List<Supplier> paginatedListSupplier() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.orElseThrow();
    }

    public List<FilterCategoriesSuppliersDto> getAllCategories() {
        List<String> categoryStrings = supplierRepository.findAllCategories();
        List<FilterCategoriesSuppliersDto> categories = new ArrayList<>();

        for (String category : categoryStrings) {
            categories.add(new FilterCategoriesSuppliersDto(category));
        }

        return categories;
    }

//    public Page<Supplier> getSuppliersByCategory(String category, Pageable pageable) {
//        return supplierRepository.findByCategory(category, pageable);
//    }

    public List<Supplier> getSuppliersByCategory(String category) {
        return supplierRepository.findByCategory(category);
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

    public List<GraphSupplierStatesDto> getSuppliersByState() {
        List<Object[]> stateCountList = supplierRepository.findSuppliersByState();
        List<GraphSupplierStatesDto> graphSupplierStatesList = new ArrayList<>();

        for (Object[] result : stateCountList) {
            String state = (String) result[0];
            Long amount = (Long) result[1];
            GraphSupplierStatesDto dto = new GraphSupplierStatesDto(state, amount);
            graphSupplierStatesList.add(dto);
        }

        return graphSupplierStatesList;
    }

    public List<GraphSupplierAmountCategoriesDto> getAmountSuppliersByCategory() {
        List<Object[]> categoryAmountList = supplierRepository.findSuppliersByCategory();
        List<GraphSupplierAmountCategoriesDto> graphSupplierCategoriesList = new ArrayList<>();

        for (Object[] result : categoryAmountList) {
            String category = (String) result[0];
            Long amount = (Long) result[1];
            GraphSupplierAmountCategoriesDto dto = new GraphSupplierAmountCategoriesDto(category, amount);
            graphSupplierCategoriesList.add(dto);
        }

        return graphSupplierCategoriesList;
    }

    public List<GraphSupplierProductsAmountDto> getProductsBySupplier() {
        List<Object[]> productAmountBySupplierList = supplierRepository.findProductsBySupplier();
        List<GraphSupplierProductsAmountDto> graphSupplierProductAmountList = new ArrayList<>();

        for (Object[] result : productAmountBySupplierList) {
            String supplierName = (String) result[0];
            Long productAmount = (Long) result[1];
            GraphSupplierProductsAmountDto dto = new GraphSupplierProductsAmountDto(supplierName, productAmount);
            graphSupplierProductAmountList.add(dto);
        }

        return graphSupplierProductAmountList;
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
