package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.ArrayDAO;
import com.revature.dao.OfferDAOSerialization;
import com.revature.pojos.Offer;
import com.revature.pojos.User;

public class OfferManagementService {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static final User User = null;

	private static List<Offer> offerDB;
	
	private static ArrayDAO<Offer> oDao = new OfferDAOSerialization();
	
	public OfferManagementService () {
		super();
		offerDB = oDao.readArray("Test_Files/testOffers");
	}
	
	public void viewOffers() {
		for(Offer o : offerDB) {
			System.out.println("[" + (offerDB.indexOf(o) + 1) + "] " + o.toString());
		}
	}
	
	public void makeOffer(User customer) {
		System.out.println("What is your offer on this car?");
		String price = scan.nextLine();
		price = price.replaceAll("\\D", "");
		int amount = Integer.parseInt(price);
		
		Offer offer = new Offer(customer, amount);
		offerDB.add(offer);
		
		oDao.createArray(offerDB, "Test_Files/testOffers");
	}
	
	public void rejectOffer(Offer o) {
		
	}
	
	public int findOffer(Offer o) {
		for(int i = 0; i < offerDB.size(); i++) {
			if(o.equals(offerDB.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
}
