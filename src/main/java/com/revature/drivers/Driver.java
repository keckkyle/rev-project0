package com.revature.drivers;

import com.revature.menus.ConsoleMenu;
import com.revature.pojos.Lot;

public class Driver {
	
	public static Lot myLot;
	
	public static void main(String[] args) {
		System.out.println("Loading...");
		System.out.println();
		
//		myLot = new Lot("Driver Lot");
		
		ConsoleMenu.menuOptions();
		
	}
	
}
