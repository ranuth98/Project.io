package com.cognizant.model;

import java.util.Date;

public class Package {
    private long packageId;
    private User userName;
    private ParcelType parcelType;
    private Date bookDate;
    private float packageWeight;
    private int distance;
    private String senderAddress;
    private String receiverAddress;
    private String status;
    private WareHouse warehouseLocation;
    private float cost;

    public Package(User userName, ParcelType parcelType, Date bookDate, float packageWeight,
            int distance, String senderAddress, String receiverAddress, String status,
            WareHouse warehouseLocation, float cost) {
        super();

        this.userName = userName;
        this.parcelType = parcelType;
        this.bookDate = bookDate;
        this.packageWeight = packageWeight;
        this.distance = distance;
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.status = status;
        this.warehouseLocation = warehouseLocation;
        this.cost = cost;
    }

    public Package() {

    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public ParcelType getParcelType() {
        return parcelType;
    }

    public void setParcelType(ParcelType parcelType) {
        this.parcelType = parcelType;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public float getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(float packageWeight) {
        this.packageWeight = packageWeight;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WareHouse getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(WareHouse warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

}
