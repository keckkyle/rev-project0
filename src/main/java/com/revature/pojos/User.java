package com.revature.pojos;

import java.io.Serializable;

public class User implements Serializable {
	
	private String name;
	private String username;
	private String password;

	public User(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username.toLowerCase();
		this.password = password;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username.toLowerCase();
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return getName()+ ": " + getUsername();
	}
	
	
}
