package com.revature.services;

import java.util.List;

import com.revature.menus.Menu;
import com.revature.pojos.Car;
import com.revature.pojos.Lot;
import com.revature.pojos.Payment;
import com.revature.pojos.User;

public class PaymentManagementService {
//	private static LoggerUtil log = new LoggerUtil();
//	private static Lot lot;
	
	private static List<Payment> paymentDB;

	public PaymentManagementService(Lot l) {
		super();
//		lot = l;
		paymentDB = l.getPayments();
	}

	public void viewPayments(){
		for(Payment p: paymentDB) {
			String car = p.getCar().getMake() + " "+ p.getCar().getModel();
			String pString = p.getCustomer().getUsername() + " owes $"+ p.getRemainingBalance() + " on " + car;
			System.out.println("[" + (paymentDB.indexOf(p) + 1) + "] " + pString);
		}
	}
	
	public void viewPayments(User user) {
		String uString = user.getUsername();
		int count = 0;
		for(Payment p: paymentDB) {
			String pString = p.getCustomer().getUsername();
			if(pString.equals(uString)) {
				System.out.println("[" + (paymentDB.indexOf(p) + 1) + "] " + p.toString());
				count += 1;
			}
		}
		if(count == 0){
			System.out.println("No cars found");
		}
	}
	
	public void getCarPayments(Car c) {
		int index = findCar(c);
		if(index != -1) {
			int length = paymentDB.get(index).getPaymentLength();
			int monthly = paymentDB.get(index).getMonthlyPayment();
			System.out.println("[" + paymentDB.get(index).getCar().toString() + "]");
			System.out.println("You have " + length + " payments of $" + monthly + " remaining.");
		}
	}
	
	public void getCarPayments(int i, User u) {
		User payUser = paymentDB.get(i).getCustomer();
		if( i < 0 || i >= paymentDB.size() || !u.getUsername().equals(payUser.getUsername()) ) {
			System.out.println("Invalid selection");
		} else {
			int length = paymentDB.get(i).getPaymentLength();
			int monthly = paymentDB.get(i).getMonthlyPayment();
			System.out.println("[" + paymentDB.get(i).getCar().toString() + "]");
			System.out.println("You have " + length + " payments of $" + monthly + " remaining.");
			Menu.userWait();
		}
	}
	
	public int findCar(Car c) {
		String carVIN = c.getVin();
		for(int i = 0; i < paymentDB.size(); i++) {
			String carI = paymentDB.get(i).getCar().getVin();
			if(carVIN.equals(carI)) {
				return i;
			}
		}
		return -1;
	}
}
