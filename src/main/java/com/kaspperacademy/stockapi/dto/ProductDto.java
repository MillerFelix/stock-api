package com.kaspperacademy.stockapi.dto;

import com.kaspperacademy.stockapi.models.Supplier;
import com.kaspperacademy.stockapi.models.Type;

import java.math.BigDecimal;

public class ProductDto {

    private String name;
    private Type type;
    private BigDecimal value;
    private Supplier supplier;
    private Integer amount;
    private String description;

    public ProductDto() {}

    public ProductDto(String name, Type type, BigDecimal value, Supplier supplier, int amount, String description) {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
