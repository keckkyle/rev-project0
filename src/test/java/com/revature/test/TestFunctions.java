package com.revature.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.revature.services.UserLoginService;
import com.revature.pojos.Customer;
import com.revature.pojos.User;

public class TestFunctions {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIfUserCreated() {
		UserLoginService uls = new UserLoginService();
		User testUser = new Customer("Kyle","keckkyle","asdfghjkl");
		assertEquals(testUser.toString(), uls.createNewUser().toString());
	}

}
