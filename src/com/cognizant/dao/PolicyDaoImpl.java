package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognizant.model.Policy;

public class PolicyDaoImpl implements PolicyDao {

    private static final String UPDATE_POLICY = "UPDATE policy SET po_name=?";
    private static final String GET_POLICY = "SELECT po_name FROM policy";

    @Override
    public boolean updatePolicy(Policy policy) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean check = false;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(UPDATE_POLICY);
            statement.setString(1, policy.getPolicyName());
            int i = statement.executeUpdate();
            if (i > 0)
                check = true;
        } catch (SQLException e) {
            System.out.println("Exception occured : " + e);
            throw new RuntimeException();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return check;
    }

    @Override
    public Policy getPolicy() {
        Connection connection = null;
        Policy policy = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(GET_POLICY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                policy = new Policy();
                policy.setPolicyName(resultSet.getString(1));
                break;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " + e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return policy;
    }

}
