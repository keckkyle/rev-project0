package com.revature.menus;

import com.revature.pojos.Employee;
import com.revature.pojos.User;

public class ConsoleMenu extends Menu {

	public ConsoleMenu() {
		super();
	}

	public static void menuOptions() {
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
			current = uls.createNewUser();
			selectUserMenu(current);
			break;
		case "2":
			current = uls.authenticateUser();
			if(current != null) {
				selectUserMenu(current);
			}
			break;
		case "3":
			System.out.println("Admin Login");
			break;
		case "4":
			System.out.println("Goodbye");
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	
	private static void selectUserMenu(User user) {
		if(user instanceof Employee) {
			EmployeeMenu.menuOptions();
		} else {
			CustomerMenu.menuOptions();
		}
	}
}
