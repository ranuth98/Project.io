package com.cognizant.junit;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cognizant.dao.ConnectionHandler;

public class ConnectionHandlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetConnection() throws IOException {
		BufferedInputStream bufferedInputStream = (BufferedInputStream) ConnectionHandler.class.getClassLoader()
				.getResourceAsStream("connection.properties");
		Properties prop = new Properties();
		prop.load(bufferedInputStream);

	}

}
