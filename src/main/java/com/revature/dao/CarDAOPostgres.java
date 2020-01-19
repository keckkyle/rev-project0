package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public void updateCar(Car car, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCar(Car car) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Car> readAllCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> readUnownedCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> readCarsByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
