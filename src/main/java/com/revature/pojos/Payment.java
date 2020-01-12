package com.revature.pojos;

public class Payment {
	
	private Car car;
	private int amount;
	
	public Payment() {
		super();
	}
	
	public Payment(Car car, int amount) {
		super();
		this.car = car;
		this.amount = amount;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return car.toString() + ": $" + amount;
	}
	
	
}
