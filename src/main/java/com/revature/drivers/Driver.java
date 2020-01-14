package com.revature.drivers;

import java.util.List;
import java.util.Scanner;

import com.revature.pojos.Car;
import com.revature.pojos.Employee;
import com.revature.pojos.Lot;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;
import com.revature.services.CarManagementService;
import com.revature.services.OfferManagementService;
import com.revature.services.PaymentManagementService;
import com.revature.services.UserLoginService;
import com.revature.util.LoggerUtil;

public class Driver {
	
	private static LoggerUtil log = new LoggerUtil();
	
	private static Scanner scan = new Scanner(System.in);
	private static Lot myLot;
	
	private static CarManagementService cms;
	private static UserLoginService uls;
	private static OfferManagementService oms;
	private static PaymentManagementService pms;
	
	private static List<Car> carDB;
	private static List<User> userDB;
	private static List<Offer> offerDB;
	private static List<Payment> paymentDB;
	
	private static User current = null;

	public static void main(String[] args) {
		
		myLot = new Lot("Driver Lot");
		cms = new CarManagementService(myLot);
		uls = new UserLoginService(myLot);
		oms = new OfferManagementService(myLot);
		pms = new PaymentManagementService(myLot);
		
		carDB = myLot.getCars();
		userDB = myLot.getUsers();
		offerDB = myLot.getOffers();
		paymentDB = myLot.getPayments();
		
		
//		User employee = new Employee("Employee", "Employee", "1234");
//		userDB.add(employee);
//		myLot.setUsers(userDB);
		
		String option = " ";
		
		do {
			System.out.println("What would you like to do?");
			System.out.println("[1] Register new user");
			System.out.println("[2] User Login");
			System.out.println("[3] Admin Features");
			System.out.println("[4] Exit");

			option = scan.nextLine();
			performUserSelection(option);
		} while (!"4".equals(option));
		
	}
	
	private static void performUserSelection(String option) {
		switch (option) {
		case "1":
			System.out.println();
			current = uls.createNewUser();
			userMenu(current);
			break;
		case "2":
			System.out.println();
			current = uls.authenticateUser();
			if(current != null) {
				userMenu(current);
			}
			break;
		case "3":
			System.out.println("Admin Login");
			break;
		case "4":
			System.out.println("Exit Program");
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	
	private static void userMenu(User user) {
		if(user instanceof Employee) {
			String option = " ";
			
			do {
				System.out.println("What would you like to do?");
				System.out.println("[1] View Cars");
				System.out.println("[2] Add Cars");
				System.out.println("[3] Remove Cars");
				System.out.println("[4] View Offers");
				System.out.println("[5] Reject Offer");
				System.out.println("[6] Accept Offer");
				System.out.println("[7] View Payments");
				System.out.println("[8] Log Out");

				option = scan.nextLine();
				employeeMenuOptions(option);
			} while (!"8".equals(option));
		} else {
			String option = " ";
			
			do {
				System.out.println("What would you like to do?");
				System.out.println("[1] View Cars");
				System.out.println("[2] Make an offer");
				System.out.println("[3] View my Cars");
				System.out.println("[4] View my Payments");
				System.out.println("[6] Log Out");

				option = scan.nextLine();
				customerMenuOptions(option);
			} while (!"6".equals(option));
		}
	}
	
	private static void employeeMenuOptions(String option){
		switch (option) {
		case "1":
			System.out.println();
			cms.viewCars();
			System.out.println();
			break;
		case "2":
			System.out.println();
			cms.addCar();
			System.out.println();
			break;
		case "3":
			System.out.println();
			System.out.println("Provide car number to remove:");
			String carNumStr = scan.nextLine();
			carNumStr = carNumStr.replaceAll("\\D", "");
			int carNum = Integer.parseInt(carNumStr);
			Car car = carDB.get(carNum-1);
			cms.removeCar(car);
			break;
		case "4":
			oms.viewOffers();
			break;
		case "5":
			System.out.println("Provide offer number to reject:");
			String rejNumStr = scan.nextLine();
			rejNumStr = rejNumStr.replaceAll("\\D", "");
			int rejNum = Integer.parseInt(rejNumStr);
			Offer rejOffer = offerDB.get(rejNum-1);
			oms.rejectOffer(rejOffer);
			break;
		case "6":
			System.out.println("Provide offer number to accept:");
			String accNumStr = scan.nextLine();
			accNumStr = accNumStr.replaceAll("\\D", "");
			int accNum = Integer.parseInt(accNumStr);
			Offer accOffer = offerDB.get(accNum-1);
			oms.acceptOffer(accOffer);
			break;
		case "7":
			pms.viewPayments();
			break;
		case "8":
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	
	private static void customerMenuOptions(String option){
		switch (option) {
		case "1":
			System.out.println();
			cms.viewCars();
			System.out.println();
			break;
		case "2":
			System.out.println();
			System.out.println("Provide car number you wish to put an offer on:");
			String carNumStr = scan.nextLine();
			carNumStr = carNumStr.replaceAll("\\D", "");
			int carNum = Integer.parseInt(carNumStr);
			Car car = carDB.get(carNum-1);
			oms.makeOffer(current, car);
			System.out.println();
			break;
		case "3":
//view my cars
			pms.viewPayments(current);
			break;
		case "4":
//view my payments
			if(paymentDB.size() > 0) {
				System.out.println();
				System.out.println("Provide car number you want to see your payment for:");
				String payNumStr = scan.nextLine();
				payNumStr = payNumStr.replaceAll("\\D", "");
				int payNum = Integer.parseInt(payNumStr);
				pms.getCarPayments(payNum-1, current);
			} else {
				System.out.println("No payment exists");
			}
			break;
		case "6":
			System.out.println();
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
}
