package com.cognizant.model;

public class ParcelType {

    private long id;
    private String parcelName;
    private float price;

    public ParcelType() {
    }

    public ParcelType(long id, String parcelName, float price) {
        super();
        this.id = id;
        this.parcelName = parcelName;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ParcelType [id=" + id + ", parcelName=" + parcelName + ", price=" + price + "]";
    }

}