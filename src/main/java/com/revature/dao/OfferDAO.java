package com.revature.dao;

import java.util.List;

import com.revature.pojos.Car;
import com.revature.pojos.Offer;
import com.revature.pojos.User;

public interface OfferDAO {
	
	public void createOffer(Offer offer);
	
	public Offer readOffer(int carId, int userID);
	
	public void updateOffer(Offer offer);
	
	public void deleteOffer(Offer offer);
	
	public List<Offer> readAllOffers();
	
	public List<Offer> readPendingOffers();
	
	public List<Offer> readOffersByUser(User user);
	
	public List<Offer> readOffersByCar(Car car);
}
