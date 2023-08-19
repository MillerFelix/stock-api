package com.kaspperacademy.stockapi.dto;

import java.util.List;

public class FilterTypeProductsDto {

    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;

    public FilterTypeProductsDto() {}
    public FilterTypeProductsDto(Long id, String name, String description, List<ProductDto> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
