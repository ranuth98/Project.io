package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.Quotation;

public class QuotationDaoImpl implements QuotationDao {

    private static final String ADD_QUOTATION = "INSERT INTO quotation(qu_distance,qu_price) VALUES(?,?)";
    private static final String UPDATE_QUOTATION = "UPDATE quotation SET qu_distance=?,qu_price=? WHERE qu_id=?";
    private static final String VIEW_QUOTATION = "SELECT qu_id,qu_distance,qu_price FROM quotation ORDER BY qu_distance";
    private static final String SELECT_QUOTATION = "SELECT qu_distance,qu_price FROM quotation WHERE qu_id=?";
    static final String QUOTATION_PRICE = "SELECT qu_price FROM quotation WHERE qu_distance >= ? ORDER BY qu_distance ASC LIMIT 1";

    @Override
    public boolean createQuotation(Quotation quotation) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean check = false;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(ADD_QUOTATION);
            statement.setInt(1, quotation.getDistance());
            statement.setFloat(2, quotation.getPrice());
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
    public boolean updateQuotation(Quotation quotation) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean check = false;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(UPDATE_QUOTATION);
            statement.setInt(1, quotation.getDistance());
            statement.setFloat(2, quotation.getPrice());
            statement.setLong(3, quotation.getId());
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
    public List<Quotation> getAllQuotation() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Quotation> quotationList = new ArrayList<Quotation>();
        connection = ConnectionHandler.getConnection();
        try {
            statement = connection.prepareStatement(VIEW_QUOTATION);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Quotation quotation = new Quotation();
                quotation.setId(resultSet.getInt(1));
                quotation.setDistance(resultSet.getInt(2));
                quotation.setPrice(resultSet.getFloat(3));
                quotationList.add(quotation);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured : " + e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection");
            }
        }
        return quotationList;
    }

    @Override
    public Quotation getQuotation(long quotationId) {
        Connection connection = null;
        Quotation quotation = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(SELECT_QUOTATION);
            statement.setLong(1, quotationId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                quotation = new Quotation();
                quotation.setId(quotationId);
                quotation.setDistance(resultSet.getInt(1));
                quotation.setPrice(resultSet.getFloat(2));
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
        return quotation;
    }

    @Override
    public Quotation getQuotation(int distance) {

        Connection connection = null;
        Quotation quotation = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHandler.getConnection();
            statement = connection.prepareStatement(QUOTATION_PRICE);
            statement.setInt(1, distance);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                quotation = new Quotation();
                quotation.setPrice(resultSet.getFloat(1));
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
        return quotation;
    }
}
