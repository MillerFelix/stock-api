package com.kaspperacademy.stockapi.dto;

public class TypeDto {

    private String name;

    private String description;

    public TypeDto() {}

    public TypeDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
