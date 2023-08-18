package com.kaspperacademy.stockapi.dto;

public class GraphSupplierAmountCategoriesDto {

    private String category;
    private Long amount;

    public GraphSupplierAmountCategoriesDto() {}
    public GraphSupplierAmountCategoriesDto(String category, Long amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public Long getAmount() {
        return amount;
    }
}
