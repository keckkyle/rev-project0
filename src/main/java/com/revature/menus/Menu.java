package com.revature.menus;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.CarDAOPostgres;
import com.revature.dao.OfferDAOPostgres;
import com.revature.dao.PaymentDAOPostgres;
import com.revature.dao.UserDAOPostgres;
import com.revature.pojos.Car;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;
import com.revature.services.CarManagementService;
import com.revature.services.OfferManagementService;
import com.revature.services.PaymentManagementService;
import com.revature.services.UserLoginService;

public class Menu {
	
	private static UserDAOPostgres uDaoP = new UserDAOPostgres();
	private static CarDAOPostgres cDaoP = new CarDAOPostgres();
	private static PaymentDAOPostgres pDaoP = new PaymentDAOPostgres();
	private static OfferDAOPostgres oDaoP = new OfferDAOPostgres();
	
	protected static Scanner scan = new Scanner(System.in);
	
	protected static UserLoginService uls = new UserLoginService();
	protected static CarManagementService cms = new CarManagementService();
	protected static OfferManagementService oms = new OfferManagementService();
	protected static PaymentManagementService pms = new PaymentManagementService();
	
	public static User current = null;
	public static List<User> userDB = uDaoP.readAllUsers();
	public static List<Car> carDB = cDaoP.readUnownedCars();
	public static List<Offer> offerDB = oDaoP.readPendingOffers();
	public static List<Payment> paymentDB = pDaoP.readAllPayments();
	
	
	
	public static void userWait() {
		System.out.println("=== Press Enter to return to menu ===");
		scan.nextLine();
	}
	
	protected static int stringToInteger(String s) {
		s = s.replaceAll("\\D", "");
		if(!"".equals(s)) {
			int num = Integer.parseInt(s);
			return num;
		}
		return -1;
	}
}
