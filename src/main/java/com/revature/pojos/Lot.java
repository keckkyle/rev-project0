package com.revature.pojos;

import java.util.ArrayList;
import java.util.List;

public class Lot {

	private List<Car> cars = new ArrayList<Car>();

	public Lot() {
		super();
	}

	public Lot(List<Car> cars) {
		super();
		this.cars = cars;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
}
