package com.cognizant.junit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cognizant.dao.EmployeeDaoImpl;
import com.cognizant.model.Employee;

public class EmployeeDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Test
	public void testGetEmployeeLong() {
	long employeeId = 5;
		Employee employee = new EmployeeDaoImpl().getEmployee(employeeId);
	 	Assert.assertEquals("Yuva", employee.getUserName());
	}

}