package com.revature.dao;

import com.revature.pojos.Lot;

public interface LotDAO {

	public void createLot(Lot l);
	
	public Lot readLot(String name);

}
