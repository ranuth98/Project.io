package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.User;

public class UserDaoImpl implements UserDao {
    private static final String CREATE_USER = "insert into user (us_first_name,us_last_name,us_gender,"
            + "us_email,us_contact_number,us_user_name,us_password,us_active) "
            + "values(?,?,?,?,?,?,?,?)";
    private static final String GET_USER_BY_USER_NAME = "select us_id, us_first_name,us_last_name,us_gender,us_email,us_contact_number,us_password,us_active "
            + " from user" + " where us_user_name= ? ";
    private static final String GET_USER_BY_ID = "select us_user_name, us_first_name,us_last_name,us_gender,us_email,us_contact_number,us_password,us_active "
            + " from user" + " where us_id= ? ";
    private static final String GET_ALL_USER_LIST = "select us_id,us_user_name, us_first_name,us_last_name,"
            + " us_gender,us_email,us_contact_number,us_password,us_active " + " from user";
    private static final String UPDATE_USER = "update user set us_first_name = ?, us_last_name= ?, us_gender = ?, "
            + "us_email= ?, us_user_name = ?, us_contact_number = ?, us_password = ?, us_active= ? "
            + "where us_id=?";// update user by user id;

    // delete user not available
    public boolean createUser(User user) throws UserNameNotAvailableException {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getEmail());
            statement.setLong(5, user.getContactNumber());
            statement.setString(6, user.getUserName());
            statement.setString(7, user.getPassword());
            statement.setString(8, user.getActive());
            if (statement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new UserNameNotAvailableException();
        } finally {
            try {
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
    }

    public User getUser(String userName) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        User user = null;
        try {
            statement = connection.prepareStatement(GET_USER_BY_USER_NAME);
            statement.setString(1, userName);
            result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(result.getLong(1));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setGender(result.getString(4));
                user.setEmail(result.getString(5));
                user.setUserName(userName);
                user.setContactNumber(result.getLong(6));
                user.setPassword(result.getString(7));
                user.setActive(result.getString(8));
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
                e.printStackTrace();
            }
        }
        return user;
    }

    public User getUser(long userId) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        User user = null;
        try {
            statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setLong(1, userId);
            result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setId(userId);
                user.setUserName(result.getString(1));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setGender(result.getString(4));
                user.setEmail(result.getString(5));
                user.setContactNumber(result.getLong(6));
                user.setPassword(result.getString(7));
                user.setActive(result.getString(8));
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
                e.printStackTrace();
            }
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        Connection connection = ConnectionHandler.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GET_ALL_USER_LIST);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setUserName(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setGender(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setContactNumber(resultSet.getLong(7));
                user.setPassword(resultSet.getString(8));
                user.setActive(resultSet.getString(9));
                userList.add(user);
            }
            return userList;
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
                e.printStackTrace();
            }
        }
        return userList;
    }

    public boolean updateUser(User user) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUserName());
            statement.setLong(6, user.getContactNumber());
            statement.setString(7, user.getPassword());
            statement.setString(8, user.getActive());
            statement.setLong(9, user.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
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
                e.printStackTrace();
            }
        }
        return false;
    }

}
