package com.kaspperacademy.stockapi.dto;

import java.math.BigDecimal;

public class FilterCategoriesSuppliersDto {

    private String category;

    public FilterCategoriesSuppliersDto() {}
    public FilterCategoriesSuppliersDto(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
