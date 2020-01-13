package com.revature.services;

import java.util.List;
import com.revature.dao.ArrayDAO;
import com.revature.dao.PaymentDAOSerialization;
import com.revature.pojos.Car;
import com.revature.pojos.Payment;

public class PaymentManagementService {
//	private static LoggerUtil log = new LoggerUtil();
	
//	private static Scanner scan = new Scanner(System.in);
	private static List<Payment> paymentDB;
	
	private static ArrayDAO<Payment> pDao = new PaymentDAOSerialization();

	public PaymentManagementService() {
		super();
		paymentDB = pDao.readArray("Test_Files/testPayments");
	}

	public void viewPayments(){
		for(Payment p: paymentDB) {
			
			System.out.println("[" + (paymentDB.indexOf(p) + 1) + "] " + p.toString());
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
