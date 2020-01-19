package com.revature.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.revature.services.CarManagementService;
import com.revature.services.OfferManagementService;
import com.revature.services.PaymentManagementService;
import com.revature.services.UserLoginService;
import com.revature.util.LoggerUtil;

import com.revature.pojos.Car;
import com.revature.pojos.Customer;
import com.revature.pojos.Lot;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;

public class TestFunctions {
	
	private static LoggerUtil log = new LoggerUtil();
	
	private static CarManagementService cms;
	private static UserLoginService uls;
	private static OfferManagementService oms;
	private static PaymentManagementService pms;
	
	private static List<Car> cars;
	private static List<User> users;
	private static List<Offer> offers;
	private static List<Payment> payments;
	
	private static Lot lot = new Lot("Test Lot");
	private static User cust1 = new Customer("Kyle", "Kyle", "1234");
	private static User cust2 = new Customer("Hannah", "Hannah", "1234");
//	private static User emp1 = new Employee("Jake", "Jake", "1234");
//	private static User emp2 = new Employee("Kate", "Kate", "1234");
	private static Car car1 = new Car("C5HL5", "Cadillac", "Escalade", "Silver", true, "2020", "0", 45590);
	private static Car car2 = new Car("14HG8", "Ford", "Focus", "Red", false, "2005", "167043", 6500);
	private static Offer offer1 = new Offer(cust1, 43500, car2);
	private static Offer offer2 = new Offer(cust2, 10500, car1);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		cms = new CarManagementService(lot);
		uls = new UserLoginService(lot);
		oms = new OfferManagementService(lot);
		pms = new PaymentManagementService(lot);
		
		cars = lot.getCars();
		users = lot.getUsers();
		offers = lot.getOffers();
		payments = lot.getPayments();
		
//		users.add(emp1);
//		users.add(emp2);
//		users.add(cust1);
//		users.add(cust2);
//		cars.add(car1);
//		cars.add(car2);
//		payments.add(new Payment(car2,44000,cust2));
//		payments.add(new Payment(car1,10500,cust1));
//		offers.add(new Offer(cust1, 43500, car2));
//		offers.add(new Offer(cust2, 41000, car2));
//		offers.add(new Offer(cust1, 11750, car1));
//		offers.add(new Offer(cust2, 10500, car1));

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

//=========================================================================
	
	@Test
	public void testIfUserCreated() {
		User newUser = uls.createNewUser();
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
//		lot.setUsers(users);
		
		List<User> deserialized = lot.getUsers();
		
		assertEquals(users.get(0).toString(), deserialized.get(0).toString());
		assertEquals(users.get(1).toString(), deserialized.get(1).toString());
		assertEquals(users.get(2).toString(), deserialized.get(2).toString());
	}
	
	@Test
	public void testCarArrayFile() {
//		lot.setCars(cars);
		
		List<Car> deserialized = lot.getCars();
		
		assertEquals(cars.get(0).toString(), deserialized.get(0).toString());
		assertEquals(cars.get(1).toString(), deserialized.get(1).toString());
	}

	@Test
	public void testOfferArrayFile() {
//		lot.setOffers(offers);
		
		List<Offer> deserialized = lot.getOffers();
		
		assertEquals(offers.get(0).toString(), deserialized.get(0).toString());
		assertEquals(offers.get(1).toString(), deserialized.get(1).toString());
	}
	
	@Test
	public void testPaymentArrayFile() {
//		lot.setPayments(payments);
		
		List<Payment> deserialized = lot.getPayments();
		
		assertEquals(payments.get(0).toString(), deserialized.get(0).toString());
		assertEquals(payments.get(1).toString(), deserialized.get(1).toString());
	}
	
	@Test 
	public void viewCarsOnLot() {
		log.debug("Test viewCars():");
		cms.viewCars();
	}
	
	@Test
	public void addCarToLot() {
		cms.viewCars();
		cms.addCar();
		cms.viewCars();
	}
	
//	@Test
//	public void removeCarFromLot() {
//		cms.viewCars();
//		cms.removeCar(car1);
//		cms.viewCars();
//	}
	
	@Test 
	public void viewAllUserPayments() {
		log.debug("Test viewPaymets():");
		pms.viewPayments();
	}
	
	@Test 
	public void viewUserPayments() {
		log.debug("Test viewPaymets(cust):");
		pms.viewPayments(cust1);
	}
	
	@Test
	public void showRemainingPayments() {
		pms.getCarPayments(car1);
	}
	
	@Test 
	public void viewAlloffers() {
		System.out.println("Test viewOffers():");
		oms.viewOffers();
	}
	
	@Test
	public void makeNewOffer() {
		oms.viewOffers();
		oms.makeOffer(cust2, car1);
		oms.viewOffers();
	}
	
	@Test
	public void rejectUserOffer() {
		oms.viewOffers();
		oms.rejectOffer(offer1);
		oms.viewOffers();
	}
	
	@Test
	public void acceptUserOffer() {
		log.debug("Offers before");
		oms.viewOffers();
		log.debug("Payments before");
		pms.viewPayments();
		oms.acceptOffer(offer2);
		log.debug("Offers after");
		oms.viewOffers();
		log.debug("Payments after");
		pms.viewPayments();
	}

}
