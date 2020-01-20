package com.revature.services;

import com.revature.menus.Menu;
import com.revature.pojos.Car;
import com.revature.pojos.Payment;
import com.revature.pojos.User;

import static com.revature.menus.Menu.paymentDB;

import java.util.List;

import com.revature.dao.PaymentDAOPostgres;

public class PaymentManagementService {
//	private static LoggerUtil log = new LoggerUtil();
	
	private static PaymentDAOPostgres pDaoP = new PaymentDAOPostgres();

	public PaymentManagementService() {
		super();
	}

	public void viewPayments(){
		for(Payment p: paymentDB) {
			String car = p.getCar().getMake() + " "+ p.getCar().getModel();
			String pString = p.getCustomer().getUsername() + " owes $"+ p.getRemainingBalance() + " on " + car;
			System.out.println("[" + (paymentDB.indexOf(p) + 1) + "] " + pString);
		}
	}
	
	public void viewPayments(User user) {
		List<Payment> userPayments = pDaoP.readPaymentsByUser(user);
		if(userPayments.size() < 1) {
			System.out.print("No cars found");
		} else {
			for(Payment p : userPayments) {
				System.out.println("[" + (userPayments.indexOf(p) + 1) + "] " + p.getCar().toString());
			}
		}
	}
	
//	public void getCarPayments(Car c) {
//		int index = findCar(c);
//		if(index != -1) {
//			int length = paymentDB.get(index).getRemainingPayments();
//			int monthly = paymentDB.get(index).getMonthlyPayment();
//			System.out.println("[" + paymentDB.get(index).getCar().toString() + "]");
//			System.out.println("You have " + length + " payments of $" + monthly + " remaining.");
//		}
//	}
	
	public void getCarPayments(int i, User user) {
		List<Payment> userPayments = pDaoP.readPaymentsByUser(user);
		if( i < 0 || i >= userPayments.size() ) {
			System.out.println("Invalid selection");
		} else {
			int length = userPayments.get(i).getRemainingPayments();
			int monthly = userPayments.get(i).getMonthlyPayment();
			System.out.println("[" + userPayments.get(i).getCar().toString() + "]");
			System.out.println("You have " + length + " payments of $" + monthly + " remaining.");
			Menu.userWait();
		}
	}
	
	public int findCar(Car c) {
		int carVIN = c.getId();
		for(int i = 0; i < paymentDB.size(); i++) {
			int carI = paymentDB.get(i).getCar().getId();
			if(carVIN == carI) {
				return i;
			}
		}
		return -1;
	}
}
