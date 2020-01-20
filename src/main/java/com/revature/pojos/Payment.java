package com.revature.pojos;

import java.io.Serializable;

public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Car car;
	private User customer;
	private int purchasePrice;
	private int remainingPayments = 60;
	private int remainingBalance;
	
	public Payment(Car car, User customer, int amount) {
		super();
		this.car = car;
		this.customer = customer;
		this.purchasePrice = amount;
		this.remainingBalance = amount;
	}

	public Payment(Car car, User customer, int purchasePrice, int remainingBalance, int remainingPayments) {
		super();
		this.car = car;
		this.customer = customer;
		this.purchasePrice = purchasePrice;
		this.remainingBalance = remainingBalance;
		this.remainingPayments = remainingPayments;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(int remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public int getRemainingPayments() {
		return remainingPayments;
	}

	public void setRemainingPayments(int remainingPayments) {
		this.remainingPayments = remainingPayments;
	}
	
	public int getMonthlyPayment() {
		return this.remainingBalance / this.remainingPayments;
	}


	@Override
	public String toString() {
		if(this.remainingBalance > 0) {
			return car.toString() + ", remaining balance: $" + this.remainingBalance;
		}
		return car.toString();
	}
	
	
}
