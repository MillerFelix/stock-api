package com.kaspperacademy.stockapi.dto;

import java.math.BigDecimal;

public class FilterProductsDto {

    private Long id;

    private String name;
    private BigDecimal value;
    private Integer amount;

    public FilterProductsDto() {}
    public FilterProductsDto(Long id, String name, BigDecimal value, Integer amount) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.amount = amount;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
