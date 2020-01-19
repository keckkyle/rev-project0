package com.revature.dao;

import java.util.List;

import com.revature.pojos.Offer;

public interface OfferDAO {
	
	public void createOffer(Offer offer);
	
	public Offer readOffer(int carId, int userID);
	
	public void updateOffer(Offer offer, int userId, int carId);
	
	public void deleteOffer(Offer offer);
	
	public List<Offer> readAllOffers();
	
	public List<Offer> readOffersByUserId(int id);
}
