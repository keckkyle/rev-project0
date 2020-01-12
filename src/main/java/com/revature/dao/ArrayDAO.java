package com.revature.dao;

import java.util.List;

public interface ArrayDAO<Type> {

	public void createArray(List<Type> cars, String name);
	
	public List<Type> readArray(String name);

}
