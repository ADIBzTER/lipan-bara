package com.dao;

import java.sql.*;

import com.config.*;
import com.bean.*;

public class AdminDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static AdminBean login(AdminBean admin) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String username = admin.getUsername();
		String password = admin.getPassword();

		// Prepared statement
		String searchQuery = "SELECT * FROM admins WHERE ad_username=? AND ad_password=?;";

		// Used to trace the process
		System.out.println("in AdminDAO");
		System.out.println("Your admin username is " + username);
		System.out.println("Your admin password is " + password);
		System.out.println("Query: " + searchQuery);

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(searchQuery);

			statement.setString(1, username);
			statement.setString(2, password);

			resultSet = statement.executeQuery();
			boolean more = resultSet.next();

			// If user does not exist
			if (!more) {
				System.out.println("Admin not registered");
				admin.setValid(false);
			}
			// If user exists
			else if (more) {
				int id = resultSet.getInt("ad_id");

				System.out.println("Welcome " + username);
				admin.setId(id);
				admin.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		// Some exception handling
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				resultSet = null;
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
				}
				statement = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
				connection = null;
			}
		}
		return admin;
	}
}
