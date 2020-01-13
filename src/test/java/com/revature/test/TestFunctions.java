package com.revature.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.revature.services.CarManagementService;
import com.revature.services.PaymentManagementService;
import com.revature.services.UserLoginService;
import com.revature.dao.ArrayDAO;
import com.revature.dao.CarDAOSerialization;
import com.revature.dao.LotDAO;
import com.revature.dao.OfferDAOSerialization;
import com.revature.dao.PaymentDAOSerialization;
import com.revature.dao.UserDAOSerialization;
import com.revature.pojos.Car;
import com.revature.pojos.Customer;
import com.revature.pojos.Employee;
import com.revature.pojos.Lot;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;

public class TestFunctions {
	
	private UserLoginService uls = new UserLoginService();
	private CarManagementService cms = new CarManagementService();
	private PaymentManagementService pms = new PaymentManagementService();
	
	private ArrayDAO<User> uDao = new UserDAOSerialization();
	private ArrayDAO<Car> cDao = new CarDAOSerialization();
	private ArrayDAO<Offer> oDao = new OfferDAOSerialization();
	private ArrayDAO<Payment> pDao = new PaymentDAOSerialization();
	
	private User user1 = new Customer("Kyle", "Kyle", "Kyle");
	private User user2 = new Customer("Bob", "Bob", "Bob");
	private User user3 = new Employee("John", "John", "John");
	private Car car1 = new Car("C5HL5", "Cadillac", "Escalade", "Silver", true, "2020", "0", 45590);
	private Car car2 = new Car("14HG8", "Ford", "Focus", "Red", false, "2005", "167043", 6500);
	private Lot lot = new Lot("Test Files");
	
	private List<Car> cars = new ArrayList<>();
	private List<Offer> offers = new ArrayList<>();
	private List<Payment> payments = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	
	private static LotDAO lotDao = new LotDAO();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		lotDao.createLotDirectory(lot);
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		cars.add(car1);
		cars.add(car2);
		offers.add(new Offer(user1, 15024));
		offers.add(new Offer(user2, 16100));
		payments.add(new Payment(car1, 15024));
		payments.add(new Payment(car2, 16100));
		
	}

	@After
	public void tearDown() throws Exception {
	}

//=========================================================================
	
	@Test
	public void testIfUserCreated() {
		User newUser = uls.createNewUser();
		lot.setUsers(uDao.readArray("Test_Files/testUsers"));
		User recent = lot.getUsers().get(lot.getUsers().size()-1);
		assertEquals(recent.toString(), newUser.toString());
	}
	
	@Test
	public void testUserLogin() {
		User testUser = uls.authenticateUser();
		User checkUser = testUser;
		assertEquals(checkUser.toString(), testUser.toString());
	}
	
	@Test
	public void testUserArrayFile() {
//		uDao.createArray(users, "Test_Files/testUsers");
		
		List<User> deserialized = uDao.readArray("Test_Files/testUsers");
		
		assertEquals(users.get(0).toString(), deserialized.get(0).toString());
		assertEquals(users.get(1).toString(), deserialized.get(1).toString());
		assertEquals(users.get(2).toString(), deserialized.get(2).toString());
	}
	
	@Test
	public void testCarArrayFile() {
//		cDao.createArray(cars, "Test_Files/testOffers");
		
		List<Car> deserialized = cDao.readArray("Test_Files/testCars");
		
		assertEquals(cars.get(0).toString(), deserialized.get(0).toString());
		assertEquals(cars.get(1).toString(), deserialized.get(1).toString());
	}

	@Test
	public void testOfferArrayFile() {
//		oDao.createArray(offers, "Test_Files/testOffers");
		
		List<Offer> deserialized = oDao.readArray("Test_Files/testOffers");
		
		assertEquals(offers.get(0).toString(), deserialized.get(0).toString());
		assertEquals(offers.get(1).toString(), deserialized.get(1).toString());
	}
	
	@Test
	public void testPaymentArrayFile() {
		pDao.createArray(payments, "Test_Files/testPayments");
		
		List<Payment> deserialized = pDao.readArray("Test_Files/testPayments");
		
		assertEquals(payments.get(0).toString(), deserialized.get(0).toString());
		assertEquals(payments.get(1).toString(), deserialized.get(1).toString());
	}
	
	@Test 
	public void viewCarsOnLot() {
		System.out.println("Test viewCars():");
		cms.viewCars();
	}
	
	@Test
	public void addCarToLot() {
		List<Car> before = cDao.readArray("Test_Files/testCars");
		
		cms.addCar();
		
		List<Car> after = cDao.readArray("Test_Files/testCars");
		assertEquals(before.size()+1, after.size());
	}
	
	@Test
	public void removeCarFromLot() {
		List<Car> before = cDao.readArray("Test_Files/testCars");
		
		cms.removeCar(car1);
		
		List<Car> after = cDao.readArray("Test_Files/testCars");
		assertEquals(before.size()-1, after.size());
	}
	
	@Test 
	public void viewUserPayments() {
		System.out.println("Test viewPaymets():");
		pms.viewPayments();
	}
	
	@Test
	public void showRemainingPayments() {
		pms.getCarPayments(car1);
	}
}
