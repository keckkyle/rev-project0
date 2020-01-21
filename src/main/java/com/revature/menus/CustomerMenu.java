package com.revature.menus;

import java.util.Scanner;

import com.revature.pojos.User;
import com.revature.services.CarManagementService;
import com.revature.services.OfferManagementService;
import com.revature.services.PaymentManagementService;

import static com.revature.services.UserInterfaceService.userWait;

public class CustomerMenu {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static CarManagementService cms = CarManagementService.getCMS();
	private static OfferManagementService oms = OfferManagementService.getOMS();
	private static PaymentManagementService pms = PaymentManagementService.getPMS();
	
	private static CustomerMenu cm;
	private static User current;
	
	private CustomerMenu() {
		super();
	}
	
	public static CustomerMenu getMenu(User user) {
		if(cm == null) {
			cm = new CustomerMenu();
		}
		current = user;
		return cm;
	}

	public void menuOptions() {
		String option = " ";
		
		do {
			System.out.println("What would you like to do?");
			System.out.println("[1] View Cars");
			System.out.println("[2] Make an offer");
			System.out.println("[3] View my Cars");
			System.out.println("[4] View my Payments");
			System.out.println("[6] Log Out");

			option = scan.nextLine();
			performUserSelection(option);
		} while (!"6".equals(option));
	}
	
	private void performUserSelection(String option) {
		switch (option) {
		case "1":
			System.out.println();
			cms.viewCars();
			userWait();
			break;
		case "2":
			System.out.println();
			oms.makeOffer(current);
			System.out.println();
			break;
		case "3":
//view my cars
			System.out.println();
			cms.viewUserCars(current);
			userWait();
			break;
		case "4":
//view my payments
			pms.getCarPayments(current);
			userWait();
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
