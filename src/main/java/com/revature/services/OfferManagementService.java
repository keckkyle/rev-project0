package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.pojos.Car;
import com.revature.pojos.Lot;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;
import com.revature.util.LoggerUtil;

public class OfferManagementService {
	
	private static LoggerUtil log = new LoggerUtil();
	
	private static Scanner scan = new Scanner(System.in);
	
	private static Lot lot;
	private static List<Offer> offerDB;
	private static List<Payment> paymentDB;
	
	public OfferManagementService (Lot l) {
		super();
		lot = l;
		offerDB = l.getOffers();
		paymentDB = l.getPayments();
	}
	
	public void viewOffers() {
		for(Offer o : offerDB) {
			System.out.println("[" + (offerDB.indexOf(o) + 1) + "] " + o.toString() + ": Listed at $" + o.getCar().getPrice());
		}
	}
	
	public void makeOffer(User customer, Car car) {
		System.out.println("["+car.toString()+"]");
		System.out.println("What is your offer on this car?");
		String price = scan.nextLine();
		price = price.replaceAll("\\D", "");
		int amount = Integer.parseInt(price);
		
		Offer offer = new Offer(customer, amount, car);
		offerDB.add(offer);
		
		lot.setOffers(offerDB);
	}
	
	public void acceptOffer(Offer o) {
		int index = findOffer(o);
		if(index != -1) {
			Offer accepted = offerDB.get(index);
			Car car = accepted.getCar();
			User customer = accepted.getCustomer();
			int amount = accepted.getAmount();
		
			Payment payment = new Payment(car, amount, customer);
			paymentDB.add(payment);
			
			offerDB.remove(index);
			
			lot.setPayments(paymentDB);
		}
		
		for(int i = offerDB.size() - 1; i > 0; i--) {
			if(o.getCar().toString().equals(offerDB.get(i).getCar().toString())) {
				log.debug(offerDB.get(i).getCar().toString());
				offerDB.remove(i);
			}
			lot.setOffers(offerDB);
		}
	}
	
	public void rejectOffer(Offer o) {
		int index = findOffer(o);
		if(index != -1) {
			offerDB.remove(index);
		}
		lot.setOffers(offerDB);
	}
	
	public int findOffer(Offer o) {
		String oUser = o.getCustomer().getUsername();
		String oCar = o.getCar().getVin() + o.getCar().getModel();
		String oStr = oUser + oCar + o.getAmount();
		for(int i = 0; i < offerDB.size(); i++) {
			String iUser = offerDB.get(i).getCustomer().getUsername();
			String iCar = offerDB.get(i).getCar().getVin() + offerDB.get(i).getCar().getModel();
			String iStr = iUser + iCar + offerDB.get(i).getAmount();
			if(oStr.equals(iStr)) {
				return i;
			}
		}
		return -1;
	}
	
}
