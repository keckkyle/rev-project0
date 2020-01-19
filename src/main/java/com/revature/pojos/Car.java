package com.revature.pojos;

import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = 4178172927577311763L;
	
	private int id;
	private String make;
	private String model;
	private String color;
	private boolean isNew;
	private String year;
	private String mileage;
	private int price;
	private int ownedBy;
	
	public Car() {
		super();
	}

	public Car(String make, String model, String color, boolean isNew, String year, String mileage, int price) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.isNew = isNew;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
	}
	
	

	public Car(int id, String make, String model, String color, boolean isNew, String year, String mileage, int price,
			int ownedBy) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.color = color;
		this.isNew = isNew;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
		this.ownedBy = ownedBy;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getMileage() {
		return mileage;
	}
	
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public int getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(int userid) {
		this.ownedBy = userid;
	}

	@Override
	public String toString() {
		String carDetails = year + " " + color + " " + make + " " + model;
		if(isNew) {
			return "New " + carDetails; 
		} 
		return "Used " + carDetails;
	}
}
