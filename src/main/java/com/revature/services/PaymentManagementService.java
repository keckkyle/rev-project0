package com.revature.services;

import com.revature.pojos.Payment;
import com.revature.pojos.User;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.PaymentDAOPostgres;

import static com.revature.services.UserInterfaceService.stringToInteger;

public class PaymentManagementService {
//	private static LoggerUtil log = new LoggerUtil();
	
	private static Scanner scan = new Scanner(System.in);
	
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
	
	public void getCarPayments(User user) {
		List<Payment> userPayments = pDao.readPaymentsByUser(user);
		if(userPayments.size() > 0) {
			for(Payment p : userPayments) {
				System.out.println("[" + (userPayments.indexOf(p) + 1) + "] Remaining Balance: $" + p.getRemainingBalance() + " on " + p.getCar().toString());
			}
			System.out.println("Provide car number you want to see your payments for:");
			String payNumStr = scan.nextLine();
			if(!"".equals(payNumStr)) {
				int payNum = stringToInteger(payNumStr);
				if(payNum > 0 && payNum <= userPayments.size()) {
					Payment selected = userPayments.get(payNum-1);
					System.out.println("[" + selected.getCar().toString() + "]");
					System.out.println("You have " + selected.getRemainingPayments() + " payments of $" + selected.getMonthlyPayment() + " remaining.");
				} else {
					System.out.println("Selection invalid");
				}
			} else {
				System.out.println("Invalid input");
			}
		} else {
			System.out.println("You have no cars to make payments on.");
		}
	}
	
//	if(paymentDB.size() > 0) {
//		System.out.println();
//		pms.viewPayments(current);
//		System.out.println("Provide car number you want to see your payment for:");
//		String payNumStr = scan.nextLine();
//		if(!"".equals(payNumStr)) {
//			int payNum = stringToInteger(payNumStr);
//			if(payNum > 0 && payNum <= paymentDB.size()) {
//				pms.getCarPayments(payNum-1, current);
//			} else {
//				System.out.println("Invalid car number provided");
//			}
//		} else {
//			System.out.println("Valid number not provided");
//		}
//	} else {
//		System.out.println("No payment exists");
//	}
//	System.out.println();
	
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
