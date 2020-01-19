package com.revature.dao;

import java.util.List;

import com.revature.pojos.User;

public interface UserDAO {
	
	public void createUser(User user);
	
	public User readUser(int id);
	
	public void updateUser(User user, int id);
	
	public void deleteUser(User car);
	
	public List<User> readAllUsers();
	
	public List<User> readUsersByType(String string);
}
