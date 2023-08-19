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

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
