package com.revature.pojos;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
	
	private List<Car> userCars = new ArrayList<Car>();

	public Customer() {
		super();
	}

	public Customer(String name, String username, String password) {
		super(name, username, password);
	}

	public List<Car> getUserCars() {
		return userCars;
	}

	public void setUserCars(List<Car> userCars) {
		this.userCars = userCars;
	}
	
}
