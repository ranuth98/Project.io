package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.ParcelType;

public interface ParcelTypeDao {

    public boolean updateParcelType(ParcelType parcelType);

    public List<ParcelType> getAllParcelType();

    public ParcelType getParcelType(long parcelId);

    public ParcelType getParcelType(String parcelName);

    public boolean createParcelType(ParcelType parcelType);

}
