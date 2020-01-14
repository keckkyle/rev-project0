package com.revature.pojos;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ArrayDAO;
import com.revature.dao.CarDAOSerialization;
import com.revature.dao.LotDAO;
import com.revature.dao.OfferDAOSerialization;
import com.revature.dao.PaymentDAOSerialization;
import com.revature.dao.UserDAOSerialization;
//import com.revature.util.LoggerUtil;

public class Lot implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	private static LoggerUtil log = new LoggerUtil();
	
	private static LotDAO lDao = new LotDAO();
	private static ArrayDAO<Car> cDao = new CarDAOSerialization();
	private static ArrayDAO<User> uDao = new UserDAOSerialization();
	private static ArrayDAO<Offer> oDao = new OfferDAOSerialization();
	private static ArrayDAO<Payment> pDao = new PaymentDAOSerialization();
	
	private String name;
	private List<Car> cars = new ArrayList<Car>();
	private List<User> users = new ArrayList<User>();
	private List<Offer> offers = new ArrayList<Offer>();
	private List<Payment> payments = new ArrayList<Payment>();
	
	private String dirPath;
	private String carFile;
	private String userFile;
	private String offerFile;
	private String paymentFile;

	
//Lot constructors
	public Lot() {
		this("unnamedlot");
	}

	public Lot(String name) {
		super();
		name = name.replaceAll(" ", "_");
		this.name = name;
		lDao.createLotDirectory(this);
		this.dirPath = this.name + "/";
		this.carFile = dirPath + "cars";
		this.userFile = dirPath + "users";
		this.offerFile = dirPath + "offers";
		this.paymentFile = dirPath + "payments";
		this.setUpLists();
	}
	
	private void setUpLists() {
		File ufile = new File(userFile + ".dat");
		if(ufile.exists()) {
			this.cars = cDao.readArray(carFile);
			this.users = uDao.readArray(userFile);
			this.offers = oDao.readArray(offerFile);
			this.payments = pDao.readArray(paymentFile);
		} else { 
			cDao.createArray(cars, carFile);
			uDao.createArray(users, userFile);
			oDao.createArray(offers, offerFile);
			pDao.createArray(payments, paymentFile);
		}
	}

//Lot getters and setters
	public String getName() {
		return name;
	}
	
	public List<Car> getCars() {
		return cars;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public List<Offer> getOffers() {
		return offers;
	}
	
	public List<Payment> getPayments() {
		return payments;
	}

	public void setCars(List<Car> cars) {
		cDao.createArray(cars, carFile);
		this.cars = cars;
	}
	
	public void setUsers(List<User> users) {
		uDao.createArray(users, userFile);
		this.users = users;
	}
	
	public void setOffers(List<Offer> offers) {
		oDao.createArray(offers, offerFile);
		this.offers = offers;
	}
	
	public void setPayments(List<Payment> payments) {
		pDao.createArray(payments, paymentFile);
		this.payments = payments;
	}
	
}
