package com.revature.dao;

import java.util.List;

import com.revature.pojos.Payment;
import com.revature.pojos.User;

public interface PaymentDAO {
	
	public void createPayment(Payment payment);
	
	public Payment readPayment(int carId, int userId);
	
	public void updatePayment(Payment payment);
	
	public void deletePayment(Payment payment);
	
	public List<Payment> readAllPayments();
	
	public List<Payment> readPaymentsByUserId(User user);
}
