package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.UserDAOPostgres;
import com.revature.pojos.Customer;
import com.revature.pojos.User;

public class UserLoginService {
	
	private static Scanner scan = new Scanner(System.in);
	private static UserDAOPostgres uDao = UserDAOPostgres.getUserDAO();
	
	private static UserLoginService uls;

	private UserLoginService() {
		super();
	}
	
	public static UserLoginService getULS() {
		if(uls == null) {
			uls = new UserLoginService();
		}
		return uls;
	}
	
	public User createNewUser() {
		String username = null;
		System.out.println();
		System.out.println("Create new user");
		System.out.println("Please provide your name:");
		String name = scan.nextLine();
		while(username == null) {
			System.out.println("Please provide a username:");
			String usernameInput = scan.nextLine();
			if(searchUsers(usernameInput.toLowerCase()) == null) {
				username = usernameInput.toLowerCase();
			} else {
				System.out.println("Username is unavailable.");
			}
		}
		System.out.println("Please provide a password:");
		String password = scan.nextLine();
		System.out.println();
		
		User newUser = new Customer(name, username, password);
		
		uDao.createUser(newUser);
		
		newUser = uDao.readUser(username);

		return newUser;
	}
	
	public User authenticateUser() {
		int count = 3;
		System.out.println();
		System.out.println("User login");
		System.out.println("Username: ");
		String username = scan.nextLine();
		User user = searchUsers(username.toLowerCase());
		if(user == null) {
			System.out.println("User not found");
			return null;
		}
		do {
			System.out.println("Password: ");
			String password = scan.nextLine();
			if(user.getPassword().equals(password)) {
				count = 0;
			} else {
				System.out.println("Incorrect password");
				System.out.println("You have " + --count + " more tries");
			}
		} while (count > 0);
		System.out.println();
		
		return user;
	}
	
	private User searchUsers(String input) {
		List<User> userDB = uDao.readAllUsers();
		for(User u : userDB) {
			if(u.getUsername().equals(input)) {
				return u;
			}
		}
		return null;
	}
	
}
