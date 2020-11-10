package com.example.logqualy.model;

import com.google.protobuf.StringValue;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Produto  implements Serializable {
    private String id;
    private String nameProduct;
    private String descriptionProduct;
    private String dateProduct;
    private String photoProduct;

    public Produto() {
    }

    public Produto(String nameProduct, String descriptionProduct) {
        Locale BRAZIL = new Locale("pt","BR");
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.dateProduct = DateFormat.getDateInstance(DateFormat.MEDIUM, BRAZIL).format(
                Calendar.getInstance().getTime()
        );
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
        return String.valueOf(dateProduct);
    }

    //    public String getDateProduct() {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        return dateProduct = dateFormat.format(date);
//    }

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
