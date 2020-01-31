package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.WareHouse;

public interface WareHouseDao {

    public boolean createWareHouse(WareHouse warehouse);

    public List<WareHouse> getAllWareHouse();

    public WareHouse getWareHouse(long wareHouseId);

    public boolean updateWareHouse(WareHouse wareHouse);

    public WareHouse getWareHouseByLocation(String Location);
}
