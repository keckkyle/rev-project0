package com.revature.dao;

import java.io.File;

import com.revature.pojos.Lot;
//import com.revature.util.LoggerUtil;

public class LotDAO {
	
//	private static LoggerUtil log = new LoggerUtil();
	
	public void createLotDirectory(Lot l) {
		String path = System.getProperty("user.dir");
		String dName = path + "\\" + l.getName();
		File directory = new File(dName);
		if(!directory.exists() && !directory.isDirectory()) {
			directory.mkdirs();
		}
	}

}
