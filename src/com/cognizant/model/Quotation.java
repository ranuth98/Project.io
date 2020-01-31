package com.cognizant.model;

public class Quotation {

    private long id;
    private int distance;
    private float price;

    public Quotation() {
    }

    public Quotation(long id, int distance, float price) {
        super();
        this.id = id;
        this.distance = distance;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
