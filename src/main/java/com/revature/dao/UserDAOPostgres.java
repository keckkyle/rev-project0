package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Customer;
import com.revature.pojos.Employee;
import com.revature.pojos.User;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggerUtil;

public class UserDAOPostgres implements UserDAO {

	private static LoggerUtil log = new LoggerUtil();
	
	@Override
	public void createUser(User user) {
		String sql = "Insert into users (full_name, username, user_password) values (?,?,?)";
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
	}

	@Override
	public User readUser(String username) {
		String sql = "Select * from users where username = ?";
		Connection conn = ConnectionFactory.getConnection();
		User user = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				user = new User(result.getInt(1), result.getString(4), result.getString(2), result.getString(3));
			}
			
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		String sql = "Update users set full_name = ?, user_password = ? where user_id = ?";
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> readAllUsers() {
		String sql = "Select * from users";

		Connection conn = ConnectionFactory.getConnection();
		
		List<User> userList = new ArrayList<User>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				if(result.getBoolean(5) == true) {
					userList.add(new Employee(result.getInt(1),result.getString(4), result.getString(2), result.getString(3)));
				} else {
					userList.add(new Customer(result.getInt(1),result.getString(4), result.getString(2), result.getString(3)));
				}	
			}
			
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		
		return userList;
	}

	@Override
	public List<User> readUsersByType(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
