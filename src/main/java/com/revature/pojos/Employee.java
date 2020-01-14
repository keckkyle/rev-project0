package com.revature.pojos;

import java.io.Serializable;

public class Employee extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	public Employee(String name, String username, String password) {
		super(name, username, password);
	}

}
