package com.kaspperacademy.stockapi.dto;

import com.kaspperacademy.stockapi.models.Type;

public class ProductDto {

    private String name;
    private Type type;
    private Double price;
    private Integer amount;
    private String description;

    public ProductDto() {}

    public ProductDto(String name, Type type, Double price, Integer amount, String description) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }


    public Double getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
