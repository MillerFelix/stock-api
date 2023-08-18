package com.kaspperacademy.stockapi.dto;

import java.math.BigDecimal;

public class GraphProductValuesDto {

    private String productName;
    private BigDecimal value;

    public GraphProductValuesDto() {}
    public GraphProductValuesDto(String productName, BigDecimal value) {
        this.productName = productName;
        this.value = value;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getValue() {
        return value;
    }
}
