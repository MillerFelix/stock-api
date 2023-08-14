package com.kaspperacademy.stockapi.dto;

public class SupplierDto {

    private String name;
    private String contact;
    private String email;
    private String country;
    private String state;
    private String adress;
    private String category;
    private String comments;

    public SupplierDto() {}

    public SupplierDto(String name, String contact, String email, String country, String state, String adress, String category, String comments) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.country = country;
        this.state = state;
        this.adress = adress;
        this.category = category;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getAdress() {
        return adress;
    }

    public String getCategory() {
        return category;
    }

    public String getComments() {
        return comments;
    }
}
