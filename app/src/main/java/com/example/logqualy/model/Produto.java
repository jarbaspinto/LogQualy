package com.example.logqualy.model;

import java.io.Serializable;

public class Produto  implements Serializable {

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
}
