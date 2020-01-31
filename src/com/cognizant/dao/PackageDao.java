package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Package;

public interface PackageDao {
    public boolean createPackage(Package packages);

    public List<Package> getAllPackage();

    public boolean updatePackage(Package packages);

    public Package getPackage(long packageId);

    public List<Package> getAllPackage(String userName);
}
