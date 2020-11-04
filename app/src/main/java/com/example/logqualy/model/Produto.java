package com.example.logqualy.model;

public class Produto {

    private int id;

    private String nameProduct;

    private String descriptionProduct;

    private String dateProduct;

    public Produto(String nameProduct, String descriptionProduct, String dateProduct) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.dateProduct = dateProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public String getDateProduct() {
        return dateProduct;
    }

    public void setDateProduct(String dateProduct) {
        this.dateProduct = dateProduct;
    }
}
