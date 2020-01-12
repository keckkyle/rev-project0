package com.revature.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lot implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private List<Car> cars = new ArrayList<Car>();
	private List<User> users = new ArrayList<User>();

	public Lot() {
		super();
		this.name = "Unnamed Lot";
	}

	public Lot(String name) {
		super();
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
