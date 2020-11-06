package com.example.logqualy.model;

import java.io.Serializable;

public class Produto  implements Serializable {
    private String id;
    private String nameProduct;
    private String descriptionProduct;
    private String dateProduct;
    private String photoProduct;

    public Produto() {
    }

    public Produto(String nameProduct, String descriptionProduct, String dateProduct, String photoProduct) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.dateProduct = dateProduct;
        this.photoProduct = photoProduct;

    }

    public String getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public String getDateProduct() {
        return dateProduct;
    }

    public String getPhotoProduct() {
        return photoProduct;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public void setDateProduct(String dateProduct) {
        this.dateProduct = dateProduct;
    }

    public void setPhotoProduct(String photoProduct) {
        this.photoProduct = photoProduct;
    }
}
