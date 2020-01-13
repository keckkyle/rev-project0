package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.ArrayDAO;
import com.revature.dao.CarDAOSerialization;
import com.revature.pojos.Car;
//import com.revature.util.LoggerUtil;

public class CarManagementService {
//	private static LoggerUtil log = new LoggerUtil();
	
	private static Scanner scan = new Scanner(System.in);
	private static List<Car> carDB;
	
	private static ArrayDAO<Car> cDao = new CarDAOSerialization();

	public CarManagementService() {
		super();
		carDB = cDao.readArray("Test_Files/testCars");
	}

	public void viewCars(){
		for(Car c: carDB) {
			
			System.out.println("[" + (carDB.indexOf(c) + 1) + "] " + c.toString() + ", mileage: " + c.getMileage() + " - Listed for $" + c.getPrice());
		}
	}
	
	public void addCar() {
		boolean isNew;
		int price;
		System.out.println("Add a new car to the lot");
		System.out.println("Is car new? [y/n]");
		String newCar = scan.nextLine().toLowerCase();
		System.out.println("Add car VIN:");
		String vin = scan.nextLine();
		System.out.println("Add car make:");
		String make = scan.nextLine();
		System.out.println("Add car model:");
		String model = scan.nextLine();
		System.out.println("Add car color:");
		String color = scan.nextLine();
		System.out.println("Add car year:");
		String year = scan.nextLine();
		System.out.println("Add car mileage:");
		String mileage = scan.nextLine();
		System.out.println("Add listing price:");
		String priceStr = scan.nextLine();
		
		if(newCar.equals("y")) {
			isNew = true;
		} else {
			isNew = false;
		}
		
		priceStr = priceStr.replaceAll("\\D", "");
		price = Integer.parseInt(priceStr);
		
		Car car = new Car(vin, make, model, color, isNew, year, mileage, price);
		carDB.add(car);
		cDao.createArray(carDB, "Test_Files/testCars");
	}
	
	public void removeCar(Car car) {
		int index = getCarIndex(car);
		carDB.remove(index);
		cDao.createArray(carDB, "Test_Files/testCars");
	}
	
	public int getCarIndex(Car car) {
		for(int i = 0; i < carDB.size(); i++) {
			if(car.getVin().equals(carDB.get(i).getVin())) {
				return i;
			}
		}
		return -1;
	}
}
