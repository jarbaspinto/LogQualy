package com.example.logqualy.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Produto  implements Serializable {
    private String id;
    private String nameProduct;
    private String descriptionProduct;
    private String dateProduct;
    private String photoProduct;

    public Produto() {
    }

    public Produto(String nameProduct, String descriptionProduct, String photoProduct) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.photoProduct = photoProduct;

    }

    public String getDataTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
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

    public void setPhotoProduct(String photoProduct) {
        this.photoProduct = photoProduct;
    }
}
