package com.cognizant.model;

public class Consignment {

    private long consignmentId;
    private WareHouse fromWareHouse;
    private WareHouse toWareHouse;
    private String consignmentStatus;

    public Consignment(long consignmentId, WareHouse fromWareHouse, WareHouse toWareHouse,
            String consignmentStatus) {
        super();
        this.consignmentId = consignmentId;
        this.fromWareHouse = fromWareHouse;
        this.toWareHouse = toWareHouse;
        this.consignmentStatus = consignmentStatus;
    }

    public Consignment() {
        // TODO Auto-generated constructor stub
    }

    public long getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(long consignmentId) {
        this.consignmentId = consignmentId;
    }

    public WareHouse getFromWareHouse() {
        return fromWareHouse;
    }

    public void setFromWareHouse(WareHouse fromWareHouse) {
        this.fromWareHouse = fromWareHouse;
    }

    public WareHouse getToWareHouse() {
        return toWareHouse;
    }

    public void setToWareHouse(WareHouse toWareHouse) {
        this.toWareHouse = toWareHouse;
    }

    public String getConsignmentStatus() {
        return consignmentStatus;
    }

    public void setConsignmentStatus(String consignmentStatus) {
        this.consignmentStatus = consignmentStatus;
    }

    @Override
    public String toString() {
        return "Consignment [consignmentId=" + consignmentId + ", fromWareHouse=" + fromWareHouse
                + ", toWareHouse=" + toWareHouse + ", consignmentStatus=" + consignmentStatus + "]";
    }

}
