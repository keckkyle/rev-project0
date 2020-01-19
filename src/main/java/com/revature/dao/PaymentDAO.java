package com.revature.dao;

import java.util.List;

import com.revature.pojos.Payment;

public interface PaymentDAO {
	
	public void createPayment(Payment payment);
	
	public Payment readPayment(int carId, int userId);
	
	public void updatePayment(Payment payment, int userId, int carId);
	
	public void deletePayment(Payment payment);
	
	public List<Payment> readAllPayments();
	
	public List<Payment> readPaymentsByUserId(int id);
}
