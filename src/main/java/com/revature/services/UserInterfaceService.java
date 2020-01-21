package com.revature.services;

import java.util.Scanner;

public class UserInterfaceService {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void userWait() {
		System.out.println("=== Press Enter to return to menu ===");
		scan.nextLine();
	}

	public static int stringToInteger(String s) {
		s = s.replaceAll("\\D", "");
		if(!"".equals(s)) {
			int num = Integer.parseInt(s);
			return num;
		}
		return -1;
	}
}
