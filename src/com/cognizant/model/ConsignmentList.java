package com.cognizant.model;

public class ConsignmentList {

    private long consignmentListId;
    private Package packageObj;
    private Consignment consignmentObj;
    private boolean packageInCons;

    public long getConsignmentListId() {
        return consignmentListId;
    }

    public void setConsignmentListId(long consignmentListId) {
        this.consignmentListId = consignmentListId;
    }

    public Package getPackageObj() {
        return packageObj;
    }

    public void setPackageObj(Package packageObj) {
        this.packageObj = packageObj;
    }

    public Consignment getConsignmentObj() {
        return consignmentObj;
    }

    public void setConsignmentObj(Consignment consignmentObj) {
        this.consignmentObj = consignmentObj;
    }

    public boolean isPackageInCons() {
        return packageInCons;
    }

    public void setPackageInCons(boolean packageInCons) {
        this.packageInCons = packageInCons;
    }

}
