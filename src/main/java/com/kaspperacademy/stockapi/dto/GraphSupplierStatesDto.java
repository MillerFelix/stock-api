package com.kaspperacademy.stockapi.dto;

public class GraphSupplierStatesDto {

    private String state;
    private Long amount;

    public GraphSupplierStatesDto() {}
    public GraphSupplierStatesDto(String state, Long amount) {
        this.state = state;
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public Long getAmount() {
        return amount;
    }
}
