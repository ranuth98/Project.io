package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.model.Package;
import com.cognizant.model.ParcelType;
import com.cognizant.model.User;
import com.cognizant.model.WareHouse;

public class PackageDaoImpl implements PackageDao {
    private static final String ADD_PACKAGE = "INSERT INTO package(pa_user_name, pa_parcel_type, pa_book_date,"
            + " pa_weight,pa_distance, pa_sender_address, pa_receiver_address, pa_cost, pa_warehouse_location, pa_status) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String VIEW_PACKAGE = "select pa_id, pa_user_name, pa_book_date, "
            + " pa_sender_address, pa_receiver_address, pa_status, pa_warehouse_location, pa_cost from package";
    private static final String UPDATE_PACKAGE = "UPDATE package SET pa_warehouse_location=?,pa_status=? WHERE pa_id=?";

    private static final String GET_PACKAGE = "SELECT pa_id,pa_user_name,pa_parcel_type,pa_weight,pa_book_date,pa_sender_address,"
            + "pa_receiver_address,pa_cost,pa_warehouse_location,pa_status"
            + " FROM package WHERE pa_id = ?";

    private static final String PACKAGE_ID = "SELECT AUTO_INCREMENT "
            + "FROM information_schema.TABLES WHERE table_name = 'package'";
    private static final String GET_USER_PACKAGE = "select pa_id, pa_parcel_type, pa_book_date, pa_weight,"
            + " pa_sender_address, pa_receiver_address, pa_cost,pa_status from package where pa_user_name=?";

    @Override
    public boolean createPackage(Package packages) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean check = false;
        try {
            statement = connection.prepareStatement(ADD_PACKAGE);

            statement.setString(1, packages.getUserName().getUserName());

            statement.setString(2, packages.getParcelType().getParcelName());
            Date date = packages.getBookDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            statement.setDate(3, sqlDate);
            statement.setFloat(4, packages.getPackageWeight());
            statement.setInt(5, packages.getDistance());
            statement.setString(6, packages.getSenderAddress());
            statement.setString(7, packages.getReceiverAddress());
            statement.setFloat(8, packages.getCost());
            statement.setString(9, packages.getWarehouseLocation().getLocation());
            statement.setString(10, packages.getStatus());
            int i = statement.executeUpdate();
            if (i > 0) {
                check = true;
            }
            preparedStatement = connection.prepareStatement(PACKAGE_ID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                packages.setPackageId(resultSet.getLong(1) - 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    @Override
    public List<Package> getAllPackage() {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Package> packageList = new ArrayList<Package>();
        try {
            statement = connection.prepareStatement(VIEW_PACKAGE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Package packages = new Package();
                packages.setPackageId(resultSet.getLong(1));
                User user = new User();
                user.setUserName(resultSet.getString(2));
                packages.setUserName(user);
                packages.setBookDate(resultSet.getDate(3));
                packages.setSenderAddress(resultSet.getString(4));
                packages.setReceiverAddress(resultSet.getString(5));
                packages.setStatus(resultSet.getString(6));
                WareHouse wareHouse = new WareHouse();
                wareHouse.setLocation(resultSet.getString(7));
                packages.setWarehouseLocation(wareHouse);
                packages.setCost(resultSet.getFloat(8));
                packageList.add(packages);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return packageList;

    }

    @Override
    public boolean updatePackage(Package packages) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean check = false;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(UPDATE_PACKAGE);
            statement.setString(1, packages.getWarehouseLocation().getLocation());
            statement.setString(2, packages.getStatus());
            statement.setLong(3, packages.getPackageId());
            int i = statement.executeUpdate();
            if (i > 0) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }

        return check;

    }

    @Override
    public Package getPackage(long packageId) {
        Connection connection = null;
        Package packages = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(GET_PACKAGE);
            statement.setLong(1, packageId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                packages = new Package();
                packages.setPackageId(packageId);
                User user = new User();
                user.setUserName(resultSet.getString(2));
                packages.setUserName(user);
                ParcelType parcelType = new ParcelType();
                parcelType.setParcelName(resultSet.getString(3));
                packages.setParcelType(parcelType);
                packages.setPackageWeight(resultSet.getInt(4));
                packages.setBookDate(resultSet.getDate(5));
                packages.setSenderAddress(resultSet.getString(6));
                packages.setReceiverAddress(resultSet.getString(7));
                packages.setCost(resultSet.getFloat(8));
                WareHouse wareHouse = new WareHouse();
                wareHouse.setLocation(resultSet.getString(9));
                packages.setWarehouseLocation(wareHouse);
                packages.setStatus(resultSet.getString(10));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " + e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return packages;
    }

    @Override
    public List<Package> getAllPackage(String userName) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Package> packageList = new ArrayList<Package>();
        try {
            statement = connection.prepareStatement(GET_USER_PACKAGE);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Package packages = new Package();
                packages.setPackageId(resultSet.getLong(1));
                ParcelType parcelType = new ParcelType();
                parcelType.setParcelName(resultSet.getString(2));
                packages.setParcelType(parcelType);
                packages.setBookDate(resultSet.getDate(3));
                packages.setPackageWeight(resultSet.getFloat(4));
                packages.setSenderAddress(resultSet.getString(5));
                packages.setReceiverAddress(resultSet.getString(6));
                packages.setCost(resultSet.getFloat(7));
                packages.setStatus(resultSet.getString(8));
                packageList.add(packages);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return packageList;
    }

}
