package com.kaspperacademy.stockapi.dto;

import com.kaspperacademy.stockapi.models.Type;

import java.math.BigDecimal;

public class ProductDto {

    private String name;
    private Type type;
    private BigDecimal value;
    private String supplier;
    private Integer amount;
    private String description;

    public ProductDto() {}

    public ProductDto(String name, Type type, BigDecimal value, String supplier, int amount, String description) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.supplier = supplier;
        this.amount = amount;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getSupplier() {
        return supplier;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
