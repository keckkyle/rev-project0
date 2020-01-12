package com.revature.drivers;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.LotDAO;
import com.revature.dao.LotDAOSerialization;
import com.revature.pojos.Lot;
import com.revature.pojos.User;
//import com.revature.pojos.User;
import com.revature.services.UserLoginService;

public class Driver {
	
	private static UserLoginService uls = new UserLoginService();
	private static Scanner scan = new Scanner(System.in);
	private static LotDAO lotDao = new LotDAOSerialization();
	private static Lot myLot;
//	private static User currentUser = null;

	public static void main(String[] args) {
		
		myLot = lotDao.readLot("testLot");
//		myLot = new Lot("testLot");

		
		String option = " ";
		
		do {
			
			System.out.println("What would you like to do?");
			System.out.println("[1] Register new user");
			System.out.println("[2] User Login");
			System.out.println("[3] Admin Features");
			System.out.println("[4] Exit");
		
			option = scan.nextLine();
			performUserSelection(option);
		} while (!"4".equals(option));
		
	}
	
	private static void performUserSelection(String option) {
		switch (option) {
		case "1":
			System.out.println();
			User nUser = uls.createNewUser();
			List<User> userList = myLot.getUsers();
			userList.add(nUser);
			myLot.setUsers(userList);
			break;
		case "2":
			System.out.println();
			User currentUser = uls.authenticateUser();
			break;
		case "3":
			System.out.println("Admin Login");
			break;
		case "4":
			lotDao.createLot(myLot);
			System.out.println("Exit");
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
}
