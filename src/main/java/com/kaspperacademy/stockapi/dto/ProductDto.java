package com.kaspperacademy.stockapi.dto;

import com.kaspperacademy.stockapi.models.Type;

import java.math.BigDecimal;

public class ProductDto {

    private String name;
    private Type type;
    private BigDecimal price;
    private int amount;
    private String description;

    public ProductDto() {}

    public ProductDto(String name, Type type, BigDecimal price, int amount, String description) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
