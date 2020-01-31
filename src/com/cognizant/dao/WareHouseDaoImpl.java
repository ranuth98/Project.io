package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.Employee;
import com.cognizant.model.WareHouse;

public class WareHouseDaoImpl implements WareHouseDao {

    private static final String ADD_WAREHOUSE_DETAILS = "insert into warehouse(wa_name,wa_location,wa_em_id,wa_capacity) values(?,?,?,?)";

    private static final String SHOW_WAREHOUSE_DETAILS = "select w.wa_id,w.wa_name,w.wa_location,w.wa_em_id,w.wa_capacity,e.em_first_name,e.em_contact_number from warehouse as w join employee as e on e.em_id=w.wa_em_id order by w.wa_id;";
    private static final String GET_WAREHOUSE_DETAILS = "select w.wa_name,w.wa_location,w.wa_em_id,w.wa_capacity,e.em_first_name from warehouse as w join employee as e on e.em_id=w.wa_em_id where w.wa_id=? order by w.wa_id;";
    private static final String MODIFY_WAREHOUSE = "update warehouse set wa_name=?,wa_location=?,wa_em_id=?,wa_capacity=? where wa_id=?";
    private static final String GET_WAREHOUSE_DETAILS_BY_LOCATION = "select wa_id, wa_name, wa_em_id, wa_capacity from warehouse where wa_location= ?;";

    public boolean createWareHouse(WareHouse wareHouse) {
        // method to add ware house details in database
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(ADD_WAREHOUSE_DETAILS);
            preparedStatement.setString(1, wareHouse.getName());
            preparedStatement.setString(2, wareHouse.getLocation());
            preparedStatement.setLong(3, wareHouse.getEmployee().getEmployeeId());
            preparedStatement.setFloat(4, wareHouse.getCapacity());
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

    public List<WareHouse> getAllWareHouse() {
        // method to view ware house details from ware house
        Connection connection = ConnectionHandler.getConnection();
        ArrayList<WareHouse> wareHouse = new ArrayList<WareHouse>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SHOW_WAREHOUSE_DETAILS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WareHouse wareHouseObj = new WareHouse();
                Employee employee = new Employee();
                wareHouseObj.setId(resultSet.getInt(1));
                wareHouseObj.setName(resultSet.getString(2));
                wareHouseObj.setLocation(resultSet.getString(3));
                employee.setEmployeeId(Long.parseLong(resultSet.getString(4)));
                wareHouseObj.setCapacity(Float.parseFloat(resultSet.getString(5)));
                employee.setFirstName(resultSet.getString(6));
                employee.setContactNumber(Long.parseLong(resultSet.getString(7)));
                wareHouseObj.setEmployee(employee);
                wareHouse.add(wareHouseObj);
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

        return wareHouse;
    }

    public WareHouse getWareHouse(long wareHouseId) {
        // method to get ware house details of particular id and display it on the
        // form-fields.
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        WareHouse wareHouseObj = null;
        try {
            preparedStatement = connection.prepareStatement(GET_WAREHOUSE_DETAILS);
            preparedStatement.setLong(1, wareHouseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wareHouseObj = new WareHouse();
                Employee employee = new Employee();
                wareHouseObj.setId(wareHouseId);
                wareHouseObj.setName(resultSet.getString(1));
                wareHouseObj.setLocation(resultSet.getString(2));
                employee.setEmployeeId(Long.parseLong(resultSet.getString(3)));
                wareHouseObj.setCapacity(Float.parseFloat(resultSet.getString(4)));
                employee.setFirstName(resultSet.getString(5));
                wareHouseObj.setEmployee(employee);

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
        return wareHouseObj;
    }

    public boolean updateWareHouse(WareHouse wareHouse) {
        // method to modify/update the ware house details.
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(MODIFY_WAREHOUSE);
            preparedStatement.setString(1, wareHouse.getName());
            preparedStatement.setString(2, wareHouse.getLocation());
            preparedStatement.setLong(3, wareHouse.getEmployee().getEmployeeId());
            preparedStatement.setFloat(4, wareHouse.getCapacity());
            preparedStatement.setLong(5, wareHouse.getId());
            int query = preparedStatement.executeUpdate();
            if (query > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
        return false;
    }

    public WareHouse getWareHouseByLocation(String Location) {
        // method to get ware house details of particular id and display it on the
        // form-fields.
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        WareHouse wareHouseObj = null;
        try {
            preparedStatement = connection.prepareStatement(GET_WAREHOUSE_DETAILS_BY_LOCATION);
            preparedStatement.setString(1, Location);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wareHouseObj = new WareHouse();
                Employee employee = new Employee();
                wareHouseObj.setId(resultSet.getLong(1));
                wareHouseObj.setName(resultSet.getString(2));
                wareHouseObj.setLocation(Location);
                employee.setEmployeeId(Long.parseLong(resultSet.getString(3)));
                wareHouseObj.setCapacity(Float.parseFloat(resultSet.getString(4)));
                wareHouseObj.setEmployee(employee);
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
        return wareHouseObj;
    }
}
