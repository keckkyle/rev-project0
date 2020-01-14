package com.revature.menus;

import java.util.Scanner;

import com.revature.pojos.Car;
import com.revature.pojos.Offer;

public class EmployeeMenu extends Menu {
	
	private static Scanner scan = new Scanner(System.in);
	
	public EmployeeMenu() {
		super();
	}

	public static void menuOptions() {
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
			performUserSelection(option);
		} while (!"8".equals(option));
		
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
}
