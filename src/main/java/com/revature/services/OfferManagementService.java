package com.revature.services;

import java.util.Scanner;

import com.revature.dao.CarDAOPostgres;
import com.revature.dao.OfferDAOPostgres;
import com.revature.dao.PaymentDAOPostgres;
import com.revature.pojos.Car;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;
//import com.revature.util.LoggerUtil;

import static com.revature.menus.Menu.carDB;
import static com.revature.menus.Menu.offerDB;
import static com.revature.menus.Menu.paymentDB;

public class OfferManagementService {
	
//	private static LoggerUtil log = new LoggerUtil();
	
	private static Scanner scan = new Scanner(System.in);
	
	private static OfferDAOPostgres oDaoP = new OfferDAOPostgres();
	private static PaymentDAOPostgres pDaoP = new PaymentDAOPostgres();
	private static CarDAOPostgres cDaoP = new CarDAOPostgres();

	public OfferManagementService () {
		super();
	}
	
	public void viewOffers() {
		for(Offer o : offerDB) {
			System.out.println("[" + (offerDB.indexOf(o) + 1) + "] " + o.toString() + ": Listed at $" + o.getCar().getPrice());
		}
	}
	
	public void makeOffer(User customer, Car car) {
		System.out.println();
		System.out.println("["+car.toString()+": Listed at $" + car.getPrice() + "]");
		System.out.println("What is your offer on this car? (Do not include $ . or , in your input)");
		String price = scan.nextLine();
		if(!"".equals(price)) {
			price = price.replaceAll("\\D", "");
			int amount = Integer.parseInt(price);
		
			Offer offer = new Offer(car, customer, amount);
			oDaoP.createOffer(offer);
			offerDB = oDaoP.readPendingOffers();

		} else {
			System.out.println("Invalid offer");
		}
	}
	
	public void acceptOffer(Offer o) {
		int index = findOffer(o);
		if(index != -1) {
			Offer accepted = offerDB.get(index);
			accepted.setStatus(2);
			Car car = accepted.getCar();
			User customer = accepted.getCustomer();
			int amount = accepted.getAmount();
			
			car.setOwnedBy(customer.getId());
		
			Payment payment = new Payment(car, customer, amount);
			
			pDaoP.createPayment(payment);
			
			oDaoP.updateOffer(accepted);
			
			cDaoP.updateCar(car);
		}
		
		for(int i = offerDB.size() - 1; i > 0; i--) {
			if( o.getCar().getId() == offerDB.get(i).getCar().getId() && offerDB.get(i).getStatus() == 1) {
				offerDB.get(i).setStatus(3);
				oDaoP.updateOffer(offerDB.get(i));
			}
		}
		
		paymentDB = pDaoP.readAllPayments();
		offerDB = oDaoP.readPendingOffers();
		carDB = cDaoP.readUnownedCars();
	}
	
	public void rejectOffer(Offer o) {
		o.setStatus(3);
		oDaoP.updateOffer(o);
		offerDB = oDaoP.readPendingOffers();
	}
	
	public int findOffer(Offer o) {
		String oUser = o.getCustomer().getUsername();
		String oCar = o.getCar().getId() + o.getCar().getModel();
		String oStr = oUser + oCar + o.getAmount();
		for(int i = 0; i < offerDB.size(); i++) {
			String iUser = offerDB.get(i).getCustomer().getUsername();
			String iCar = offerDB.get(i).getCar().getId() + offerDB.get(i).getCar().getModel();
			String iStr = iUser + iCar + offerDB.get(i).getAmount();
			if(oStr.equals(iStr)) {
				return i;
			}
		}
		return -1;
	}
	
}
