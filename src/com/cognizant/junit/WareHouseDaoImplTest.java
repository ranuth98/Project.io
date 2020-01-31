package com.cognizant.junit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cognizant.dao.WareHouseDaoImpl;
import com.cognizant.model.WareHouse;

public class WareHouseDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testCreateWareHouse() {
		long id = 9;
		WareHouse warehouse = new WareHouseDaoImpl().getWareHouse(id);
		Assert.assertEquals("Salem", warehouse.getLocation());
	}

}
