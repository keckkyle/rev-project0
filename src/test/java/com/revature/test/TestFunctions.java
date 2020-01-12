package com.revature.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.revature.services.UserLoginService;
import com.revature.dao.ArrayDAO;
import com.revature.dao.CarDAOSerialization;
import com.revature.dao.OfferDAOSerialization;
import com.revature.dao.PaymentDAOSerialization;
import com.revature.dao.UserDAOSerialization;
import com.revature.pojos.Car;
import com.revature.pojos.Customer;
import com.revature.pojos.Employee;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;

public class TestFunctions {
	
	User user1 = new Customer("Kyle", "Kyle", "Kyle");
	User user2 = new Customer("Bob", "Bob", "Bob");
	User user3 = new Employee("John", "John", "John");
	Car car1 = new Car("C5HL5", "Cadillac", "Escalade", "Silver", true, 2020, 0, 45590);
	Car car2 = new Car("14HG8", "Ford", "Focus", "Red", false, 2005, 167043, 6500);

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
		assertEquals(user1.toString(), uls.createNewUser().toString());
	}
	
	@Test
	public void testUserArrayFile() {
		ArrayDAO<User> uDao = new UserDAOSerialization();
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		uDao.createArray(users, "testFiles/testUsers");
		
		List<User> deserialized = uDao.readArray("testFiles/testUsers");
		
		assertEquals(users.get(0).getUsername(), deserialized.get(0).getUsername());
		assertEquals(users.get(1).getUsername(), deserialized.get(1).getUsername());
		assertEquals(users.get(2).getUsername(), deserialized.get(2).getUsername());
	}
	
	@Test
	public void testCarArrayFile() {
		ArrayDAO<Car> cDao = new CarDAOSerialization();
		List<Car> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		cDao.createArray(cars, "TestFiles/testCars");
		
		List<Car> deserialized = cDao.readArray("TestFiles/testCars");
		
		assertEquals(cars.get(0).toString(), deserialized.get(0).toString());
		assertEquals(cars.get(1).toString(), deserialized.get(1).toString());
	}

	@Test
	public void testOfferArrayFile() {
		ArrayDAO<Offer> oDao = new OfferDAOSerialization();
		List<Offer> offers = new ArrayList<>();
		offers.add(new Offer(user1, 15024));
		offers.add(new Offer(user2, 16100));
		oDao.createArray(offers, "TestFiles/testOffers");
		
		List<Offer> deserialized = oDao.readArray("TestFiles/testOffers");
		
		assertEquals(offers.get(0).toString(), deserialized.get(0).toString());
		assertEquals(offers.get(1).toString(), deserialized.get(1).toString());
	}
	
	@Test
	public void testPaymentArrayFile() {
		ArrayDAO<Payment> pDao = new PaymentDAOSerialization();
		List<Payment> payments = new ArrayList<>();
		payments.add(new Payment(car1, 15024));
		payments.add(new Payment(car2, 16100));
		pDao.createArray(payments, "TestFiles/testPayments");
		
		List<Payment> deserialized = pDao.readArray("TestFiles/testPayments");
		
		assertEquals(payments.get(0).toString(), deserialized.get(0).toString());
		assertEquals(payments.get(1).toString(), deserialized.get(1).toString());
	}
}
