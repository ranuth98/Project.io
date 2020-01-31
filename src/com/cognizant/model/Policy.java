package com.cognizant.model;

public class Policy {

    private long id;
    private String policyName;

    public Policy() {
    }

    public Policy(long id, String policyName) {
        super();
        this.id = id;
        this.policyName = policyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

}
