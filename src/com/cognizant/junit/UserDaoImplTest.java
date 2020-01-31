package com.cognizant.junit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cognizant.dao.UserDaoImpl;
import com.cognizant.model.User;

public class UserDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetUser() {
		long userId = 3;
		User user = new UserDaoImpl().getUser(userId);
		Assert.assertEquals("B", user.getLastName());
	}
}
