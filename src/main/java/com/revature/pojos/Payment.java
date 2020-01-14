package com.revature.pojos;

import java.io.Serializable;

public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Car car;
	private User customer;
	private int purchasePrice;
	private int paymentLength = 60;
	private int remainingBalance;
	private int monthlyPayment;
	
	public Payment(Car car, int amount, User customer) {
		super();
		this.car = car;
		this.customer = customer;
		this.purchasePrice = amount;
		this.remainingBalance = amount;
		this.monthlyPayment = calcMonthly();
	}
	
	private int calcMonthly() {
		return this.purchasePrice / this.paymentLength;
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
	
	public int getPaymentLength() {
		return paymentLength;
	}

	public int getMonthlyPayment() {
		return monthlyPayment;
	}

	@Override
	public String toString() {
		if(this.remainingBalance > 0) {
			return car.toString() + ", remaining balance: $" + this.remainingBalance;
		}
		return car.toString();
	}
	
	
}
