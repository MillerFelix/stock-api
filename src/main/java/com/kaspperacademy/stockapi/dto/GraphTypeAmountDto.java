package com.kaspperacademy.stockapi.dto;

public class GraphTypeAmountDto {

    private String typeName;
    private Integer amount;

    public GraphTypeAmountDto() {}
    public GraphTypeAmountDto(String typeName, Integer amount) {
        this.typeName = typeName;
        this.amount = amount;
    }

    public String getTypeName() {
        return typeName;
    }
    public Integer getAmount() {
        return amount;
    }
}
