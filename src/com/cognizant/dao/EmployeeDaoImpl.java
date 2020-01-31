package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

    private static final String CREATE_EMPLOYEE = "insert into employee(em_user_name ,em_first_name, em_last_name, em_gender,"
            + "em_email, em_contact_number, em_password, em_salary, em_designation,"
            + "em_permanent_address, em_correspondence_address, em_login_type, em_active) "
            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String GET_EMPLOYEE_BY_ID = "select em_user_name,em_first_name,em_last_name,em_gender,"
            + "em_email,em_contact_number,em_password,em_salary,em_designation,em_permanent_address,"
            + "em_correspondence_address,em_login_type,em_active from employee Where em_id = ? ";

    private static final String GET_EMPLOYEE_BY_USER_NAME = "select em_id,em_first_name,em_last_name,em_gender,"
            + "em_email,em_contact_number,em_password,em_salary,em_designation,em_permanent_address,"
            + "em_correspondence_address,em_login_type,em_active from employee Where em_user_name = ? ";

    private static final String GET_EMPLOYEE_LIST = "Select em_id,em_user_name ,em_first_name, em_last_name, em_gender,"
            + "em_email, em_contact_number, em_salary, em_designation,"
            + "em_permanent_address, em_correspondence_address, em_login_type, em_active from employee";

    private static final String UPDATE_ADMIN = "update employee set em_user_name= ?,em_first_name= ?,em_last_name=?,em_gender=?,"
            + " em_email=?, em_contact_number=? ,em_password=? ,em_salary=? ,em_designation=? ,em_permanent_address=? ,"
            + "em_correspondence_address=? ,em_login_type=?, em_active=?  where em_id= ?";

    private static final String DELETE_EMPLOYEE = "delete from employee where em_id = ?";

    private static final String CHECK_EMPLOYEE = "select w.wa_id,w.wa_em_id from warehouse as w join employee as e on e.em_id=w.wa_em_id where e.em_id=?";

    private static final String GET_WAREHOUSE_EMPLOYEE = "select e.em_id,e.em_first_name from employee as e join warehouse as w where e.em_id not in(select wa_em_id from warehouse) group by e.em_id";

    public boolean createEmployee(Employee employee) throws UserNameNotAvailableException {
        Connection connection = null;
        PreparedStatement statement = null; 
        connection = ConnectionHandler.getConnection();
        try {
            statement = connection.prepareStatement(CREATE_EMPLOYEE);
            statement.setString(1, employee.getUserName());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setString(5, employee.getEmail());
            statement.setLong(6, employee.getContactNumber());
            statement.setString(7, employee.getPassword());
            statement.setLong(8, employee.getSalary());
            statement.setString(9, employee.getDesignation());
            statement.setString(10, employee.getPermanentAddress());
            statement.setString(11, employee.getCorrespondenceAddress());
            statement.setString(12, String.valueOf(employee.getLoginType()));
            statement.setString(13, String.valueOf(employee.getActive()));
            int query = statement.executeUpdate();
            if (query > 0) {
                return true;
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
        return false;
    }

    public Employee getEmployee(long employeeId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        connection = ConnectionHandler.getConnection();
        Employee employee = null;
        try {
            statement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
            statement.setLong(1, employeeId);
            result = statement.executeQuery();

            if (result.next()) {
                employee = new Employee();
                employee.setEmployeeId(employeeId);
                employee.setUserName(result.getString(1));
                employee.setFirstName(result.getString(2));
                employee.setLastName(result.getString(3));
                employee.setGender(result.getString(4));
                employee.setEmail(result.getString(5));
                employee.setContactNumber(result.getLong(6));
                employee.setPassword(result.getString(7));
                employee.setSalary(result.getLong(8));
                employee.setDesignation(result.getString(9));
                employee.setPermanentAddress(result.getString(10));
                employee.setCorrespondenceAddress(result.getString(11));
                employee.setLoginType(result.getString(12).charAt(0));
                employee.setActive(result.getString(13).charAt(0));
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
        return employee;
    }

    public Employee getEmployee(String userName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        connection = ConnectionHandler.getConnection();
        Employee employee = null;
        try {
            statement = connection.prepareStatement(GET_EMPLOYEE_BY_USER_NAME);
            statement.setString(1, userName);
            result = statement.executeQuery();
            if (result.next()) {
                employee = new Employee();
                employee.setUserName(userName);
                employee.setEmployeeId(result.getLong(1));
                employee.setFirstName(result.getString(2));
                employee.setLastName(result.getString(3));
                employee.setGender(result.getString(4));
                employee.setEmail(result.getString(5));
                employee.setContactNumber(result.getLong(6));
                employee.setPassword(result.getString(7));
                employee.setSalary(result.getLong(8));
                employee.setDesignation(result.getString(9));
                employee.setPermanentAddress(result.getString(10));
                employee.setCorrespondenceAddress(result.getString(11));
                employee.setLoginType(result.getString(12).charAt(0));
                employee.setActive(result.getString(13).charAt(0));
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
        return employee;
    }

    public List<Employee> getAllEmployees() {
        Connection connection = null;
        PreparedStatement statement = null;
        connection = ConnectionHandler.getConnection();
        ResultSet result = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(GET_EMPLOYEE_LIST);
            result = statement.executeQuery();

            while (result.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(result.getLong(1));
                employee.setUserName(result.getString(2));
                employee.setFirstName(result.getString(3));
                employee.setLastName(result.getString(4));
                employee.setGender(result.getString(5));
                employee.setEmail(result.getString(6));
                employee.setContactNumber(result.getLong(7));
                employee.setSalary(result.getLong(8));
                employee.setDesignation(result.getString(9));
                employee.setPermanentAddress(result.getString(10));
                employee.setCorrespondenceAddress(result.getString(11));
                employee.setLoginType(result.getString(12).charAt(0));
                employee.setActive(result.getString(13).charAt(0));
                employeeList.add(employee);
            }

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
        return employeeList;

    }

    public boolean updateEmployee(Employee employee) {
        Connection connection = ConnectionHandler.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_ADMIN);
            statement.setString(1, employee.getUserName());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setString(5, employee.getEmail());
            statement.setLong(6, employee.getContactNumber());
            statement.setString(7, employee.getPassword());
            statement.setLong(8, employee.getSalary());
            statement.setString(9, employee.getDesignation());
            statement.setString(10, employee.getPermanentAddress());
            statement.setString(11, employee.getCorrespondenceAddress());
            statement.setString(12, String.valueOf(employee.getLoginType()));
            statement.setString(13, String.valueOf(employee.getActive()));
            statement.setLong(14, employee.getEmployeeId());
            int query = statement.executeUpdate();

            if (query > 0) {
                return true;
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

    public int checkEmployee(long employeeId) {
        Connection connection = null;
        PreparedStatement statement = null;
        connection = ConnectionHandler.getConnection();
        ResultSet resultSet = null;
        int warehouse = 0;

        try {
            statement = connection.prepareStatement(CHECK_EMPLOYEE);
            statement.setLong(1, employeeId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getLong(2) != 0) {
                    warehouse = resultSet.getInt(1);
                    return warehouse;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public boolean removeEmployee(long employeeId) {
        Connection connection = null;
        PreparedStatement statement = null;
        connection = ConnectionHandler.getConnection();

        try {
            statement = connection.prepareStatement(DELETE_EMPLOYEE);
            statement.setLong(1, employeeId);
            int query = statement.executeUpdate();
            if (query > 0) {
                return true;
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

    public List<Employee> getWareHouseEmployees() {
        Connection connection = null;
        PreparedStatement statement = null;
        connection = ConnectionHandler.getConnection();
        ResultSet result = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(GET_WAREHOUSE_EMPLOYEE);
            result = statement.executeQuery();

            while (result.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(result.getLong(1));
                employee.setFirstName(result.getString(2));
                employeeList.add(employee);
            }

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
        return employeeList;

    }
}
