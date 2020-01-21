package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Car;
import com.revature.pojos.Offer;
import com.revature.pojos.User;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;

public class OfferDAOPostgres implements OfferDAO {
	
	private static LoggerUtil log = new LoggerUtil();
	
	private static CarDAOPostgres cDao = CarDAOPostgres.getCarDAO();
	private static UserDAOPostgres uDao = UserDAOPostgres.getUserDAO();
	
	private static OfferDAOPostgres oDao;
	
	private OfferDAOPostgres() {
		super();
	}
	
	public static OfferDAOPostgres getOfferDAO(){
		if(oDao == null) {
			oDao = new OfferDAOPostgres();
		}
		return oDao;
	}

	@Override
	public void createOffer(Offer offer) {
		String sql = "Insert into offer (car_id, user_id, amount) values (?,?,?)";
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, offer.getCar().getId());
			stmt.setInt(2, offer.getCustomer().getId());
			stmt.setInt(3, offer.getAmount());
			
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
	public Offer readOffer(int carId, int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOffer(Offer offer) {
		String sql = "update offer set status = ?, amount = ? where car_id = ? and user_id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, offer.getStatus());
			stmt.setInt(2, offer.getAmount());
			stmt.setInt(3, offer.getCar().getId());
			stmt.setInt(4, offer.getCustomer().getId());
			
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
	public void deleteOffer(Offer offer) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Offer> readAllOffers() {
		String sql = "Select * from offer";
		
		return returnOffers(sql);
	}
	
	@Override
	public List<Offer> readPendingOffers(){
		String sql = "Select * from offer where status = 1 order by car_id";
		
		return returnOffers(sql);
	}

	@Override
	public List<Offer> readOffersByUser(User user) {
		String sql = "Select * from offer where user_id = ?";
		
		int id = user.getId();
		
		return returnOffers(sql, id);
	}

	@Override
	public List<Offer> readOffersByCar(Car car) {
		String sql = "Select * from offer where car_id = ?";
		
		int id = car.getId();
		
		return returnOffers(sql, id);
	}
	
	private List<Offer> returnOffers(String sql){
		Connection conn = ConnectionFactory.getConnection();
		
		List<Offer> offers = new ArrayList<Offer>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()){
				Car car = cDao.readCar(result.getInt(1));
				User user = uDao.readUser(result.getInt(2));
				offers.add(new Offer(car, user, result.getInt(3)));
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
		
		return offers;
	}
	
	private List<Offer> returnOffers(String sql, int id) {
		Connection conn = ConnectionFactory.getConnection();
		
		List<Offer> offers = new ArrayList<Offer>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				Car car = cDao.readCar(result.getInt(1));
				User user = uDao.readUser(result.getInt(2));
				offers.add(new Offer(car, user, result.getInt(3)));
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
		
		return offers;
	}

}
