package com.dao;

import java.sql.*;

import com.config.*;
import com.bean.*;

public class CustomerDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Login user
	public static CustomerBean login(CustomerBean customer) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String username = customer.getUsername();
		String password = customer.getPassword();

		// Prepared statement
		String searchQuery = "SELECT * FROM customers WHERE cust_username=? AND cust_password=?;";

		// Used to trace the process
		System.out.println("in CustomerDAO.login");
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
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
				System.out.println("Sorry, you are not a registered user! " + "Please sign up first");
				customer.setValid(false);
			}
			// If user exists
			else if (more) {
				int id = resultSet.getInt("cust_id");
				String name = resultSet.getString("cust_name");
				String address = resultSet.getString("cust_address");
				String phone = resultSet.getString("cust_phone");

				System.out.println("Welcome " + username);
				customer.setId(id);
				customer.setName(name);
				customer.setAddress(address);
				customer.setPhone(phone);
				customer.setValid(true);
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
		return customer;
	}

	// Register new user
	public static void addCustomer(CustomerBean customer) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String name = customer.getName();
		String username = customer.getUsername();
		String password = customer.getPassword();
		String address = customer.getAddress();
		String phone = customer.getPhone();

		// Prepared statement
		String sql = "INSERT INTO customers (cust_name, cust_username, cust_password, cust_address, cust_phone) "
				+ "VALUES (?, ?, ?, ?, ?);";

		// Used to trace the process
		System.out.println("in CustomerDAO.addCustomer");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, name);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, address);
			statement.setString(5, phone);

			statement.executeUpdate();
			System.out.println(username + "added to database.");

		} catch (Exception ex) {
			System.out.println("Cannot add user to db: " + ex);
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
	}

	// Check availability of username
	public static boolean isUsernameAvailable(String username) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		boolean isAvailable = false;

		// Prepared statement
		String sql = "SELECT cust_username FROM customers WHERE cust_username=?;";

		// Used to trace the process
		System.out.println("in CustomerDAO.isUsernameAvailable");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, username);

			resultSet = statement.executeQuery();
			boolean more = resultSet.next();

			// Username available
			if (!more) {
				isAvailable = true;
			}
			// Username not available
			else {
				isAvailable = false;
			}

		} catch (Exception ex) {
			System.out.println("Error in CustomerDAO.isUsernameAvailable " + ex);
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
		return isAvailable;
	}

	// Get only one purchase
	public static CustomerBean getOne( int custId) {

		// Preparing some objects/variable
		CustomerBean customer = new CustomerBean();
		String sql = "SELECT * from customers WHERE cust_id=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("In CustomerDAO.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, custId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				
				// Customer
				customer.setId(resultSet.getInt("cust_id"));
				customer.setName(resultSet.getString("cust_name"));
				customer.setUsername(resultSet.getString("cust_username"));
				customer.setAddress(resultSet.getString("cust_address"));
				customer.setPhone(resultSet.getString("cust_phone"));

			}
		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.getOne " + ex);
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
		return customer;
	}
}
