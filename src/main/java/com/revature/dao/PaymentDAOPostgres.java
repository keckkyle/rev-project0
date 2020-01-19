package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.pojos.Payment;
import com.revature.pojos.User;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;

public class PaymentDAOPostgres implements PaymentDAO {
	
	private static LoggerUtil log = new LoggerUtil();

	@Override
	public void createPayment(Payment payment) {
		String sql = "Insert into payment (car_id, user_id, purchase_price, remaining_balance, term_length) values (?,?,?,?,?)";
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, payment.getCar().getId());
			stmt.setInt(2, payment.getCustomer().getId());
			stmt.setInt(3, payment.getPurchasePrice());
			stmt.setInt(4, payment.getRemainingBalance());
			stmt.setInt(5, payment.getPaymentLength());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
	}

	@Override
	public Payment readPayment(int carId, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePayment(Payment payment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePayment(Payment payment) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Payment> readAllPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> readPaymentsByUserId(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
