package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.ParcelType;

public class ParcelTypeDaoImpl implements ParcelTypeDao {

    private static final String ADD_PARCEL_TYPE = "INSERT INTO parcel_type(pa_ty_name,pa_ty_price) VALUES(?,?)";
    private static final String UPDATE_PARCEL_TYPE = "UPDATE parcel_type SET pa_ty_name=?,pa_ty_price=? WHERE pa_ty_id=?";
    private static final String VIEW_PARCEL_TYPE = "SELECT pa_ty_id,pa_ty_name,pa_ty_price FROM parcel_type";
    private static final String SELECT_PARCEL_ID = "SELECT pa_ty_name,pa_ty_price FROM parcel_type WHERE pa_ty_id=?";
    private static final String SELECT_PARCEL_NAME = "SELECT pa_ty_price FROM parcel_type WHERE pa_ty_name=?";

    @Override
    public boolean createParcelType(ParcelType parcelType) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean check = false;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(ADD_PARCEL_TYPE);
            statement.setString(1, parcelType.getParcelName());
            statement.setFloat(2, parcelType.getPrice());
            int i = statement.executeUpdate();
            if (i > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " + e);
            throw new RuntimeException();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return check;
    }

    @Override
    public boolean updateParcelType(ParcelType parcelType) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean check = false;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(UPDATE_PARCEL_TYPE);
            statement.setString(1, parcelType.getParcelName());
            statement.setFloat(2, parcelType.getPrice());
            statement.setLong(3, parcelType.getId());
            int i = statement.executeUpdate();
            if (i > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " + e);
            throw new RuntimeException();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return check;
    }

    @Override
    public List<ParcelType> getAllParcelType() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ParcelType> parcelList = new ArrayList<ParcelType>();
        connection = ConnectionHandler.getConnection();
        try {
            statement = connection.prepareStatement(VIEW_PARCEL_TYPE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ParcelType parcel = new ParcelType();
                parcel.setId(resultSet.getInt(1));
                parcel.setParcelName(resultSet.getString(2));
                parcel.setPrice(resultSet.getFloat(3));
                parcelList.add(parcel);
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
        return parcelList;
    }

    @Override
    public ParcelType getParcelType(long parcelId) {
        Connection connection = null;
        ParcelType parcel = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(SELECT_PARCEL_ID);
            statement.setLong(1, parcelId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parcel = new ParcelType();
                parcel.setId(parcelId);
                parcel.setParcelName(resultSet.getString(1));
                parcel.setPrice(resultSet.getFloat(2));
                break;
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
        return parcel;
    }

    @Override
    public ParcelType getParcelType(String parcelName) {
        Connection connection = null;
        ParcelType parcel = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(SELECT_PARCEL_NAME);
            statement.setString(1, parcelName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                parcel = new ParcelType();
                parcel.setPrice(resultSet.getFloat(1));
                break;
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
        return parcel;
    }

}
