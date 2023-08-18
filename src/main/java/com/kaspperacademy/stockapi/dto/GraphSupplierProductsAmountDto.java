package com.kaspperacademy.stockapi.dto;

public class GraphSupplierProductsAmountDto {

    private String nameSupplier;
    private Long amountProducts;

    public GraphSupplierProductsAmountDto() {}
    public GraphSupplierProductsAmountDto(String nameSupplier, Long amountProducts) {
        this.nameSupplier = nameSupplier;
        this.amountProducts = amountProducts;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public Long getAmountProducts() {
        return amountProducts;
    }
}
