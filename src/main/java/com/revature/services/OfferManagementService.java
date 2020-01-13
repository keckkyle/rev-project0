package com.revature.services;

import java.util.List;

import com.revature.dao.ArrayDAO;
import com.revature.dao.OfferDAOSerialization;
import com.revature.pojos.Offer;

public class OfferManagementService {
	
	private static List<Offer> offerDB;
	
	private static ArrayDAO<Offer> oDao = new OfferDAOSerialization();
	
	public OfferManagementService () {
		super();
		offerDB = oDao.readArray("Test_Files/testOffers");
	}
	
	
}
