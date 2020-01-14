package com.revature.menus;

import java.util.Scanner;

import com.revature.pojos.Car;

public class CustomerMenu extends Menu {
	
	private static Scanner scan = new Scanner(System.in);
	
	public CustomerMenu() {
		super();
	}

	public static void menuOptions() {
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
	
	private static void performUserSelection(String option) {
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
