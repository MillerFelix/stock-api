package com.kaspperacademy.stockapi.dto;

import java.math.BigDecimal;

public class GraphTypeValuesDto {

    private String typeName;
    private BigDecimal value;

    public GraphTypeValuesDto() {}
    public GraphTypeValuesDto(String typeName, BigDecimal value) {
        this.typeName = typeName;
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public BigDecimal getValue() {
        return value;
    }
}
