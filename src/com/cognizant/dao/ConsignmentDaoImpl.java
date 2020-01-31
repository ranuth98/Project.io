package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.Consignment;
import com.cognizant.model.WareHouse;

public class ConsignmentDaoImpl implements ConsignmentDao {
    // ------------------------------------------------------------------------------
    // warehouse object in the consignment dint fetch all the data from the data
    // base....
    // implies some variables in the ware house object are still null and
    // zero.......
    // to get those data access it by warehouse id from the data
    // base(warehouseDaoImpl)
    // -------------------------------------------------------------------------------
    private static final String CREATE_CONSIGNMENT = "insert into consignment(co_from_warehouse_id,co_to_warehouse_id,co_consignment_status) values(?,?,?);";

    private static final String GET_ALL_CONSIGNMENT = "select c.co_id,c.co_from_warehouse_id,c.co_to_warehouse_id,c.co_consignment_status,w.wa_location,u.wa_location from consignment c join warehouse w join warehouse u on c.co_from_warehouse_id = w.wa_id and co_to_warehouse_id=u.wa_id;\r\n";

    private static final String GET_CONSIGNMENT_BY_ID = "select c.co_id,c.co_from_warehouse_id,c.co_to_warehouse_id,c.co_consignment_status,w.wa_location,u.wa_location from consignment c join warehouse w join warehouse u on c.co_from_warehouse_id = w.wa_id and co_to_warehouse_id=u.wa_id where c.co_id = ?;\r\n";

    private static final String UPDATE_CONSIGNMENT = "update consignment"
            + " set co_from_warehouse_id=?, co_to_warehouse_id=?, co_consignment_status = ? "
            + " where co_id = ?  ";

    public boolean createConsignment(Consignment consignment) {

        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(CREATE_CONSIGNMENT);
            preparedStatement.setLong(1, consignment.getFromWareHouse().getId());
            preparedStatement.setLong(2, consignment.getToWareHouse().getId());
            preparedStatement.setString(3, consignment.getConsignmentStatus());
            int query = preparedStatement.executeUpdate();
            if (query > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public List<Consignment> getAllConsignments() {

        Connection connection = ConnectionHandler.getConnection();
        ArrayList<Consignment> consignment = new ArrayList<Consignment>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(GET_ALL_CONSIGNMENT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Consignment consignmentObj = new Consignment();
                WareHouse fromWareHouse = new WareHouse();
                WareHouse toWareHouse = new WareHouse();
                consignmentObj.setConsignmentId(resultSet.getInt(1));
                fromWareHouse.setId((Long.parseLong(resultSet.getString(2))));
                toWareHouse.setId((Long.parseLong(resultSet.getString(3))));
                consignmentObj.setConsignmentStatus(resultSet.getString(4));
                fromWareHouse.setLocation(resultSet.getString(5));
                toWareHouse.setLocation(resultSet.getString(6));
                consignmentObj.setFromWareHouse(fromWareHouse);
                consignmentObj.setToWareHouse(toWareHouse);
                consignment.add(consignmentObj);

            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
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

        return consignment;
    }

    public Consignment getConsignment(long consignmentId) {
// returns null if the requested consignmentId is not available in the consignment list
// some of the warehouse obj variables are still null..
// to get those data access it by warehouse id from the data base(warehouseDaoImpl)
//-----------------------------
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Consignment consignmentObj = null;
        try {
            preparedStatement = connection.prepareStatement(GET_CONSIGNMENT_BY_ID);
            preparedStatement.setLong(1, consignmentId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                consignmentObj = new Consignment();
                WareHouse fromWareHouse = new WareHouse();
                WareHouse toWareHouse = new WareHouse();
                consignmentObj.setConsignmentId(resultSet.getInt(1));
                fromWareHouse.setId((Long.parseLong(resultSet.getString(2))));
                toWareHouse.setId((Long.parseLong(resultSet.getString(3))));
                consignmentObj.setConsignmentStatus(resultSet.getString(4));
                fromWareHouse.setLocation(resultSet.getString(5));
                toWareHouse.setLocation(resultSet.getString(6));
                consignmentObj.setFromWareHouse(fromWareHouse);
                consignmentObj.setToWareHouse(toWareHouse);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
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

        return consignmentObj;
    }

    public boolean updateConsignment(Consignment consignment) {

        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_CONSIGNMENT);
            preparedStatement.setLong(1, consignment.getFromWareHouse().getId());
            preparedStatement.setLong(2, consignment.getToWareHouse().getId());
            preparedStatement.setString(3, consignment.getConsignmentStatus());
            preparedStatement.setLong(4, consignment.getConsignmentId());
            int query = preparedStatement.executeUpdate();
            if (query > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}