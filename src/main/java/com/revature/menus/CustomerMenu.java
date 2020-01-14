package com.revature.menus;

import com.revature.pojos.Car;

public class CustomerMenu extends Menu {
	
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
			userWait();
			break;
		case "2":
			System.out.println();
			cms.viewCars();
			System.out.println("Provide car number you wish to put an offer on:");
			String carNumStr = scan.nextLine();
			if(!"".equals(carNumStr)) {
				int carNum = stringToInteger(carNumStr);
				if(carNum > 0 && carNum <= carDB.size()) {
					Car car = carDB.get(carNum-1);
					oms.makeOffer(current, car);
				} else {
					System.out.println("Invalid car number");
				}
			} else {
				System.out.println("No offer made");
			}
			System.out.println();
			break;
		case "3":
//view my cars
			System.out.println();
			pms.viewPayments(current);
			userWait();
			break;
		case "4":
//view my payments
			if(paymentDB.size() > 0) {
				System.out.println();
				pms.viewPayments(current);
				System.out.println("Provide car number you want to see your payment for:");
				String payNumStr = scan.nextLine();
				if(!"".equals(payNumStr)) {
					int payNum = stringToInteger(payNumStr);
					if(payNum > 0 && payNum <= paymentDB.size()) {
						pms.getCarPayments(payNum-1, current);
					} else {
						System.out.println("Invalid car number provided");
					}
				} else {
					System.out.println("Valid number not provided");
				}
			} else {
				System.out.println("No payment exists");
			}
			System.out.println();
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
