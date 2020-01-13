package com.revature.pojos;

import java.io.Serializable;

public class Payment implements Serializable {
	
	private Car car;
	private int purchasePrice;
	private int paymentLength = 60;
	private int remainingBalance;
	private int monthlyPayment;
	
	public Payment() {
		super();
	}
	
	public Payment(Car car, int amount) {
		super();
		this.car = car;
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

	public void setMonthlyPayment(int monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	@Override
	public String toString() {
		if(this.remainingBalance > 0) {
			return car.toString() + ", remaining balance: $" + this.remainingBalance;
		}
		return car.toString();
	}
	
	
}
