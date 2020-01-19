package com.revature.pojos;

import java.io.Serializable;

public class Offer implements Serializable {
	private static final long serialVersionUID = -6666306052535321535L;
	
	private User customer;
	private Car car;
	private int amount;
	private int status;
	
	public Offer(Car car, User customer, int amount) {
		super();
		this.customer = customer;
		this.amount = amount;
		this.car = car;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return customer.getUsername() + " offers $" + amount + " for " + car.toString();
	}
	
	
	 
}
