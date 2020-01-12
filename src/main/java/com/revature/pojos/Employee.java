package com.revature.pojos;

import java.io.Serializable;

public class Employee extends User implements Serializable {

	public Employee(String name, String username, String password) {
		super(name, username, password);
	}

}
