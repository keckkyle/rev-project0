package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Car;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;

public class CarDAOPostgres implements CarDAO {
	
	private static LoggerUtil log = new LoggerUtil();

	@Override
	public void createCar(Car car) {
		String sql = "Insert into car (make, model, color, model_year, mileage, price, is_new) values (?,?,?,?,?,?,?)";
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, car.getMake());
			stmt.setString(2, car.getModel());
			stmt.setString(3, car.getColor());
			stmt.setString(4, car.getYear());
			stmt.setString(5, car.getMileage());
			stmt.setInt(6, car.getPrice());
			stmt.setBoolean(7, car.getIsNew());
			
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
	public Car readCar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCar(Car car) {
		String sql = "Update car set make = ?, model = ?, color = ?, model_year = ?, mileage = ?, price = ?, is_new = ?, user_id = ? where car_id = ?";
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, car.getMake());
			stmt.setString(2, car.getModel());
			stmt.setString(3, car.getColor());
			stmt.setString(4, car.getYear());
			stmt.setString(5, car.getMileage());
			stmt.setInt(6, car.getPrice());
			stmt.setBoolean(7, car.getIsNew());
			stmt.setInt(8, car.getOwnedBy());
			stmt.setInt(9, car.getId());
			
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
	public void deleteCar(Car car) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Car> readAllCars() {
		String sql = "Select * from car";
		
		return returnCarList(sql);
	}

	@Override
	public List<Car> readUnownedCars() {
		String sql = "Select * from car where user_id is null";
		
		return returnCarList(sql);
	}

	@Override
	public List<Car> readCarsByUserId(int id) {
		String sql = "Select * from car where user_id = " + id;
		
		return returnCarList(sql);
	}
	
	
	private List<Car> returnCarList(String sql){
		Connection conn = ConnectionFactory.getConnection();
		
		List<Car> carList = new ArrayList<Car>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				carList.add(new Car(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getBoolean(9), result.getString(5), result.getString(6), result.getInt(7), result.getInt(8)));
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
		
		return carList;
	}

}
