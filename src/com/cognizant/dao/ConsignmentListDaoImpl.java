package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.Consignment;
import com.cognizant.model.ConsignmentList;
import com.cognizant.model.Package;

public class ConsignmentListDaoImpl implements ConsignmentListDao {

    private static final String CREATE_CONSIGNMENT_LIST = " Insert Into consignment_list(co_li_co_id,co_li_pa_id,co_li_package_status) "
            + " values(?,?,?)";

//	private static final String GET_ALL_LIST="select co_li_id, co_li_co_id, co_li_pa_id, co_li_status "
//			+ "from consignment_list"; 

    private static final String GET_CONSIGNMENT_LIST = "select co_li_id, co_li_co_id, co_li_pa_id, co_li_package_status "
            + "from consignment_list " + "where co_li_id = ?";
    private static final String GET_ALL_LIST_BY_PA_ID = "select co_li_id, co_li_co_id, co_li_pa_id, co_li_package_status "
            + "from consignment_list " + "where co_li_pa_id = ?";

    private static final String GET_ALL_LIST_BY_CO_ID = "select co_li_id, co_li_co_id, co_li_pa_id, co_li_package_status "
            + " from consignment_list" + " where co_li_co_id = ?";
    private static final String UPDATE_CONSIGNMENT_LIST = "UPDATE consignment_list "
            + " SET co_li_co_id= ?,co_li_pa_id =? ,co_li_package_status =? " + " WHERE co_li_id =?";

    public boolean createConsignmentList(ConsignmentList consignmentList) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(CREATE_CONSIGNMENT_LIST);
            statement.setLong(1, consignmentList.getConsignmentObj().getConsignmentId());
            statement.setLong(2, consignmentList.getPackageObj().getPackageId());
            statement.setBoolean(3, consignmentList.isPackageInCons());
            int query = statement.executeUpdate();
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

    public ConsignmentList getConsignmentList(long consignmentListId) {
        // check for null in the return list
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        ConsignmentList consignmentList = null;
        try {
            statement = connection.prepareStatement(GET_CONSIGNMENT_LIST);
            statement.setLong(1, consignmentListId);
            result = statement.executeQuery();

            if (result.next()) {
                consignmentList = new ConsignmentList();
                consignmentList.setConsignmentListId(result.getLong(1));
                Consignment consignmentObj = new Consignment();
                consignmentObj.setConsignmentId(result.getLong(2));
                consignmentList.setConsignmentObj(consignmentObj);
                Package packageObj = new Package();
                packageObj.setPackageId(result.getLong(3));
                consignmentList.setPackageObj(packageObj);
                consignmentList.setPackageInCons(result.getBoolean(4));
            }
            return consignmentList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return consignmentList;
    }

    public List<ConsignmentList> getListByPackage(long packageId) {
// check for null in the return list 
        Connection connection = ConnectionHandler.getConnection();
        ArrayList<ConsignmentList> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        ConsignmentList consignmentList = null;
        try {
            statement = connection.prepareStatement(GET_ALL_LIST_BY_PA_ID);
            statement.setLong(1, packageId);
            result = statement.executeQuery();

            while (result.next()) {
                consignmentList = new ConsignmentList();
                consignmentList.setConsignmentListId(result.getLong(1));
                Consignment consignmentObj = new Consignment();
                consignmentObj.setConsignmentId(result.getLong(2));
                consignmentList.setConsignmentObj(consignmentObj);
                Package packageObj = new Package();
                packageObj.setPackageId(result.getLong(3));
                consignmentList.setPackageObj(packageObj);
                consignmentList.setPackageInCons(result.getBoolean(4));
                list.add(consignmentList);
            }
            return list;

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public List<ConsignmentList> getListByConsignment(long consignmentId) {
        // check for null in the return list
        Connection connection = ConnectionHandler.getConnection();
        ArrayList<ConsignmentList> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        ConsignmentList consignmentList = null;
        try {
            statement = connection.prepareStatement(GET_ALL_LIST_BY_CO_ID);
            statement.setLong(1, consignmentId);
            result = statement.executeQuery();

            while (result.next()) {

                consignmentList = new ConsignmentList();
                consignmentList.setConsignmentListId(result.getLong(1));
                Consignment consignmentObj = new Consignment();
                consignmentObj.setConsignmentId(result.getLong(2));
                consignmentList.setConsignmentObj(consignmentObj);
                Package packageObj = new Package();
                packageObj.setPackageId(result.getLong(3));
                consignmentList.setPackageObj(packageObj);
                consignmentList.setPackageInCons(result.getBoolean(4));
                list.add(consignmentList);
            }
            return list;

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public boolean updateConsignmentList(ConsignmentList consignmentList) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_CONSIGNMENT_LIST);
            statement.setLong(1, consignmentList.getConsignmentObj().getConsignmentId());
            statement.setLong(2, consignmentList.getPackageObj().getPackageId());
            statement.setBoolean(3, consignmentList.isPackageInCons());
            statement.setLong(4, consignmentList.getConsignmentListId());
            int query = statement.executeUpdate();
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