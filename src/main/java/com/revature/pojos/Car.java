package com.revature.pojos;

import java.util.ArrayList;
import java.util.List;

public class Car {

	private String vin;
	private String make;
	private String model;
	private String color;
	private boolean isNew;
	private int year;
	private int mileage;
	private int price;
	private List<Offer> offers = new ArrayList<Offer>();
	
	public Car() {
		super();
	}

	public Car(String vin, String make, String model, String color, boolean isNew, int year, int mileage, int price) {
		super();
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.color = color;
		this.isNew = isNew;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
	}

	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMileage() {
		return mileage;
	}
	
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	
}
