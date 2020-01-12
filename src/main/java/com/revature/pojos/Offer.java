package com.revature.pojos;

import java.io.Serializable;

public class Offer implements Serializable {
	
	private User customer;
	private int amount;
	
	public Offer() {
		super();
	}
	
	public Offer(User customer, int amount) {
		super();
		this.customer = customer;
		this.amount = amount;
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

	@Override
	public String toString() {
		return customer.toString() + " offers $" + amount;
	}
	
	
	 
}