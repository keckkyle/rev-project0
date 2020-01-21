package com.revature.menus;

import java.util.Scanner;

import com.revature.pojos.Employee;
import com.revature.pojos.User;
import com.revature.services.UserLoginService;

public class AccessMenu {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static UserLoginService uls = UserLoginService.getULS();
	
	private static User current;

	public AccessMenu() {
		super();
	}

	public void menuOptions() {
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
	
	private void performUserSelection(String option) {
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
			System.out.println("Access denied");
			System.out.println();
			break;
		case "4":
			System.out.println("Goodbye");
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	
	private void selectUserMenu(User user) {
		if(user instanceof Employee) {
			EmployeeMenu em = EmployeeMenu.getMenu();
			em.menuOptions();
		} else {
			CustomerMenu cm = CustomerMenu.getMenu(user);
			cm.menuOptions();
		}
	}
}
