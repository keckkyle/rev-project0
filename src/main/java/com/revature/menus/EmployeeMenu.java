package com.revature.menus;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.CarDAOPostgres;
import com.revature.dao.OfferDAOPostgres;
import com.revature.pojos.Car;
import com.revature.pojos.Offer;
import com.revature.services.CarManagementService;
import com.revature.services.OfferManagementService;
import com.revature.services.PaymentManagementService;

import static com.revature.services.UserInterfaceService.userWait;
import static com.revature.services.UserInterfaceService.stringToInteger;


public class EmployeeMenu {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static CarManagementService cms = CarManagementService.getCMS();
	private static OfferManagementService oms = OfferManagementService.getOMS();
	private static PaymentManagementService pms = PaymentManagementService.getPMS();
	
	private static CarDAOPostgres cDao = CarDAOPostgres.getCarDAO();
	private static OfferDAOPostgres oDao = OfferDAOPostgres.getOfferDAO();
	
	private static EmployeeMenu em;
	
	private EmployeeMenu() {
		super();
	}
	
	public static EmployeeMenu getMenu() {
		if(em==null) {
			em = new EmployeeMenu();
		}
		return em;
	}
	

	public void menuOptions() {
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
	
	private void performUserSelection(String option) {
		List<Car> carDB = cDao.readUnownedCars();
		List<Offer> offerDB = oDao.readAllOffers();
		switch (option) {
		case "1":
			System.out.println();
			cms.viewCars();
			userWait();
			break;
		case "2":
			System.out.println();
			cms.addCar();
			System.out.println();
			break;
		case "3":
			System.out.println();
			cms.viewCars();
			System.out.println("Provide car number to remove:");
			String carNumStr = scan.nextLine();
			if(!"".equals(carNumStr)) {
				int carNum = stringToInteger(carNumStr);
				if(carNum > 0 && carNum <= carDB.size()) {
					Car car = carDB.get(carNum-1);
					cms.removeCar(car);
				} else {
					System.out.println("Invalid car number");
				}
			} else {
				System.out.println("No car removed");
			}
			System.out.println();
			break;
		case "4":
			System.out.println();
			oms.viewOffers();
			userWait();
			break;
		case "5":
			System.out.println();
			oms.viewOffers();
			System.out.println("Provide offer number to reject:");
			String rejNumStr = scan.nextLine();
			if(!"".equals(rejNumStr)) {
				int rejNum = stringToInteger(rejNumStr);
				if(rejNum > 0 && rejNum <= offerDB.size()) {
					Offer rejOffer = offerDB.get(rejNum-1);
					oms.rejectOffer(rejOffer);
				} else {
					System.out.println("Invalid offer number");
				}
			} else {
				System.out.println("No offer rejected");
			}
			System.out.println();
			break;
		case "6":
			System.out.println();
			oms.viewOffers();
			System.out.println("Provide offer number to accept:");
			String accNumStr = scan.nextLine();
			if(!"".equals(accNumStr)) {
				int accNum = stringToInteger(accNumStr);
				if(accNum > 0 && accNum <= offerDB.size()) {
					Offer accOffer = offerDB.get(accNum-1);
					oms.acceptOffer(accOffer);
				} else {
					System.out.println("Invalid offer number");
				}
			} else {
				System.out.println("No offer accepted");
			}
			System.out.println();
			break;
		case "7":
			System.out.println();
			pms.viewPayments();
			userWait();
			break;
		case "8":
			System.out.println();
			break;
		default:
			System.out.println();
			System.out.println("Invalid input");
			break;
		}
	}
}
