package com.revature.dao;

import java.util.List;

import com.revature.pojos.Car;

public interface CarDAO {
	
	public void createCar(Car car);
	
	public Car readCar(int id);
	
	public void updateCar(Car car, int id);
	
	public void deleteCar(Car car);
	
	public List<Car> readAllCars();
	
	public List<Car> readUnownedCars();
	
	public List<Car> readCarsByUserId(int id);
}
