package com.cognizant.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConnectionHandlerTest.class, EmployeeDaoImplTest.class, UserDaoImplTest.class,
		WareHouseDaoImplTest.class })
public class AllTests {

}
