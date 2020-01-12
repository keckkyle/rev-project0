package com.revature.drivers;

import java.util.Scanner;

import com.revature.dao.LotDAO;
import com.revature.pojos.Lot;
import com.revature.pojos.User;
import com.revature.services.UserLoginService;
//import com.revature.util.LoggerUtil;

public class Driver {
	
//	private static LoggerUtil log = new LoggerUtil();
	private static UserLoginService uls = new UserLoginService();
	private static Scanner scan = new Scanner(System.in);
	private static LotDAO lotDao = new LotDAO();
	private static Lot myLot;
	
	private static User current;

	public static void main(String[] args) {
		
		myLot = new Lot("testLot");

		lotDao.createLotDirectory(myLot);
		
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
		
		System.out.println(current.toString());
	}
	
	private static void performUserSelection(String option) {
		switch (option) {
		case "1":
			System.out.println();
			current = uls.createNewUser();
			break;
		case "2":
			System.out.println();
			current = uls.authenticateUser();
			break;
		case "3":
			System.out.println("Admin Login");
			break;
		case "4":
			System.out.println("Exit");
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
}
