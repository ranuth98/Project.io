package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Employee;

public interface EmployeeDao {

    public boolean createEmployee(Employee employee) throws UserNameNotAvailableException;

    public Employee getEmployee(long employeeId);

    public Employee getEmployee(String userName);

    public List<Employee> getAllEmployees();

    public boolean updateEmployee(Employee employee);

    public int checkEmployee(long employeeId);

    public boolean removeEmployee(long employeeId);

    public List<Employee> getWareHouseEmployees();

}
