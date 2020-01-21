package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.CarDAOPostgres;
import com.revature.dao.OfferDAOPostgres;
import com.revature.dao.PaymentDAOPostgres;
import com.revature.pojos.Car;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;
//import com.revature.util.LoggerUtil;

public class OfferManagementService {
	
//	private static LoggerUtil log = new LoggerUtil();
	
	private static Scanner scan = new Scanner(System.in);
	
	private static OfferDAOPostgres oDao = OfferDAOPostgres.getOfferDAO();
	private static PaymentDAOPostgres pDao = PaymentDAOPostgres.getPaymentDAO();
	private static CarDAOPostgres cDao = CarDAOPostgres.getCarDAO();
	
	private static OfferManagementService oms;

	private OfferManagementService() {
		super();
	}
	
	public static OfferManagementService getOMS() {
		if(oms == null) {
			oms = new OfferManagementService();
		}
		return oms;
	}
	
	public void viewOffers() {
		List<Offer> offerDB = oDao.readPendingOffers();
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
			oDao.createOffer(offer);

		} else {
			System.out.println("Invalid offer");
		}
	}
	
	public void acceptOffer(Offer o) {
		List<Offer> offerDB = oDao.readPendingOffers();
		int index = findOffer(o, offerDB);
		if(index != -1) {
			Offer accepted = offerDB.get(index);
			accepted.setStatus(2);
			Car car = accepted.getCar();
			User customer = accepted.getCustomer();
			int amount = accepted.getAmount();
			
			car.setOwnedBy(customer.getId());
		
			Payment payment = new Payment(car, customer, amount);
			
			pDao.createPayment(payment);
			
			oDao.updateOffer(accepted);
			
			cDao.updateCar(car);
		}
		
		for(int i = offerDB.size() - 1; i > 0; i--) {
			if( o.getCar().getId() == offerDB.get(i).getCar().getId() && offerDB.get(i).getStatus() == 1) {
				offerDB.get(i).setStatus(3);
				oDao.updateOffer(offerDB.get(i));
			}
		}

	}
	
	public void rejectOffer(Offer o) {
		o.setStatus(3);
		oDao.updateOffer(o);
	}
	
	public int findOffer(Offer o, List<Offer> offerDB) {
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
