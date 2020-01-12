package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Car;
import com.revature.util.LoggerUtil;

public class CarDAOSerialization implements ArrayDAO<Car> {
	
	private static LoggerUtil log = new LoggerUtil();

	@Override
	public void createArray(List<Car> cars, String name) {
		String filename = name + ".dat";
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos);){
			oos.writeObject(cars);
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}

	@Override
	public List<Car> readArray(String name) {
		String filename = name + ".dat";
		
		List<Car> ret = new ArrayList<>();
		
		try (FileInputStream fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis);){
			ret = (ArrayList) ois.readObject();
		} catch (FileNotFoundException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.debug(e.getMessage());
		}
		
		return ret;
	}

}
