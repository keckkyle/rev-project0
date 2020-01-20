package com.revature.services;

import java.util.Scanner;

import com.revature.dao.CarDAOPostgres;
import com.revature.pojos.Car;

import static com.revature.menus.Menu.carDB;

public class CarManagementService {
//	private static LoggerUtil log = new LoggerUtil();
	
	private static Scanner scan = new Scanner(System.in);
	private static CarDAOPostgres cDaoP = new CarDAOPostgres();

	public CarManagementService() {
		super();
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
		
		Car car = new Car(make, model, color, isNew, year, mileage, price);
		cDaoP.createCar(car);
		carDB = cDaoP.readUnownedCars();
	}
	
	public void removeCar(Car car) {
		cDaoP.deleteCar(car);
		carDB = cDaoP.readUnownedCars();
	}
	
	public int getCarIndex(Car car) {
		String checkCar = car.getId() + car.getMake();
		for(int i = 0; i < carDB.size(); i++) {
			String carI = carDB.get(i).getId() + carDB.get(i).getMake();
			if(checkCar.equals(carI)) {
				return i;
			}
		}
		return -1;
	}
}
