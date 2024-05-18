package com.example.carkh;

public class MyDataModel {
    private String title;
    private String description;
    private int imageResId;


    private  float price;

    public MyDataModel(String title, String description, int imageResId, float price) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public float getPrice() {return price;}
}
