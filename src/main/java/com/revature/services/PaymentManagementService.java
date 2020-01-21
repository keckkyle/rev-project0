package com.revature.services;

import com.revature.pojos.Payment;
import com.revature.pojos.User;

import java.util.List;

import com.revature.dao.PaymentDAOPostgres;

import static com.revature.services.UserInterfaceService.userWait;

public class PaymentManagementService {
//	private static LoggerUtil log = new LoggerUtil();
	
	private static PaymentDAOPostgres pDao = PaymentDAOPostgres.getPaymentDAO();
	
	private static PaymentManagementService pms;

	private PaymentManagementService() {
		super();
	}
	
	public static PaymentManagementService getPMS() {
		if(pms == null) {
			pms = new PaymentManagementService();
		}
		return pms;
	}

	public void viewPayments(){
		List<Payment> paymentDB = pDao.readAllPayments();
		for(Payment p: paymentDB) {
			String car = p.getCar().getMake() + " "+ p.getCar().getModel();
			String pString = p.getCustomer().getUsername() + " owes $"+ p.getRemainingBalance() + " on " + car;
			System.out.println("[" + (paymentDB.indexOf(p) + 1) + "] " + pString);
		}
	}
	
	public void viewPayments(User user) {
		List<Payment> userPayments = pDao.readPaymentsByUser(user);
		if(userPayments.size() < 1) {
			System.out.println("No cars found");
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
		List<Payment> userPayments = pDao.readPaymentsByUser(user);
		if( i < 0 || i >= userPayments.size() ) {
			System.out.println("Invalid selection");
		} else {
			int length = userPayments.get(i).getRemainingPayments();
			int monthly = userPayments.get(i).getMonthlyPayment();
			System.out.println("[" + userPayments.get(i).getCar().toString() + "]");
			System.out.println("You have " + length + " payments of $" + monthly + " remaining.");
			userWait();
		}
	}
	
//	public int findCar(Car c) {
//		int carVIN = c.getId();
//		for(int i = 0; i < paymentDB.size(); i++) {
//			int carI = paymentDB.get(i).getCar().getId();
//			if(carVIN == carI) {
//				return i;
//			}
//		}
//		return -1;
//	}
}
