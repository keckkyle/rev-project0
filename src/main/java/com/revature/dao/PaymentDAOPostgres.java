package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Car;
import com.revature.pojos.Payment;
import com.revature.pojos.User;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;

public class PaymentDAOPostgres implements PaymentDAO {
	
	private static LoggerUtil log = new LoggerUtil();
	
	private static CarDAOPostgres cDao = new CarDAOPostgres();
	private static UserDAOPostgres uDao = new UserDAOPostgres();

	@Override
	public void createPayment(Payment payment) {
		String sql = "Insert into payment (car_id, user_id, purchase_price, remaining_balance, remaining_payments) values (?,?,?,?,?)";
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, payment.getCar().getId());
			stmt.setInt(2, payment.getCustomer().getId());
			stmt.setInt(3, payment.getPurchasePrice());
			stmt.setInt(4, payment.getRemainingBalance());
			stmt.setInt(5, payment.getRemainingPayments());
			
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
		String sql = "Select * from payment";
		
		Connection conn = ConnectionFactory.getConnection();
		
		List<Payment> paymentList = new ArrayList<Payment>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()){
				Car car = cDao.readCar(result.getInt(1));
				User user = uDao.readUser(result.getInt(2));
				paymentList.add(new Payment(car, user, result.getInt(3), result.getInt(4), result.getInt(5)));
			}
			
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		
		return paymentList;
	}

	@Override
	public List<Payment> readPaymentsByUser(User user) {
		String sql = "Select * from payment where user_id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		
		List<Payment> paymentList = new ArrayList<Payment>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, user.getId());
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Car car = cDao.readCar(result.getInt(1));
				paymentList.add(new Payment(car, user, result.getInt(3), result.getInt(4), result.getInt(5)));
			}
			
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		
		return paymentList;
	}

}
