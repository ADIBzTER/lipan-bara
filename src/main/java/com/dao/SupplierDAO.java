package com.dao;

import java.sql.*;
import java.util.*;

import com.config.*;
import com.bean.*;

public class SupplierDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static List<SupplierBean> getAll() {

		// Preparing some objects/variable
		List<SupplierBean> supplierList = new LinkedList<>();
		String sql = "SELECT * FROM suppliers ORDER BY supp_id;";

		Statement statement = null;

		// Trace process
		System.out.println("in SupplierBean.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				SupplierBean supplier = new SupplierBean();
				supplier.setId(resultSet.getInt("supp_id"));
				supplier.setName(resultSet.getString("supp_name"));
				supplier.setAddress(resultSet.getString("supp_address"));
				supplier.setPhone(resultSet.getString("supp_phone"));

				supplierList.add(supplier);
			}
		} catch (Exception ex) {
			System.out.println("Error in SupplierDAO.getAll: " + ex);
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
		return supplierList;
	}

	// Get only one supplier
	public static SupplierBean getOne(int supplierId) {

		// Preparing some objects/variable
		SupplierBean supplier = new SupplierBean();
		String sql = "SELECT * from suppliers WHERE supp_id=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("in SupplierDAO.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, supplierId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Product
				supplier.setId(resultSet.getInt("supp_id"));
				supplier.setName(resultSet.getString("supp_name"));
				supplier.setAddress(resultSet.getString("supp_address"));
				supplier.setPhone(resultSet.getString("supp_phone"));

			}
		} catch (Exception ex) {
			System.out.println("Error in SupplierDAO.getOne " + ex);
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
		return supplier;
	}

	// Add one supplier
	public static void addOne(SupplierBean supplier) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String name = supplier.getName();
		String address = supplier.getAddress();
		String phone = supplier.getPhone();

		// Prepared statement
		String sql = "INSERT INTO suppliers (supp_name, supp_address, supp_phone) " + "VALUES (?, ?, ?);";

		// Used to trace the process
		System.out.println("in SupplierDAO.addOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, name);
			statement.setString(2, address);
			statement.setString(3, phone);

			statement.executeUpdate();
			System.out.println("Supplier added to database.");

		} catch (Exception ex) {
			System.out.println("Error in CartDAO.addToCart " + ex);
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

	public static void deleteOne(int supplierId) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "DELETE FROM suppliers WHERE supp_id = ?";

		// Used to trace the process
		System.out.println("in SupplierDAO.deleteOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, supplierId);

			statement.executeUpdate();
			System.out.println("Supplier remove from database.");

		} catch (Exception ex) {
			System.out.println("Error in SupplierDAO.deleteOne" + ex);
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

	public static void updateOne(SupplierBean supplier) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "UPDATE suppliers SET supp_name = ?, supp_address = ?, supp_phone = ? WHERE supp_id = ?";

		// Used to trace the process
		System.out.println("in SupplierDAO.updateOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, supplier.getName());
			statement.setString(2, supplier.getAddress());
			statement.setString(3, supplier.getPhone());
			statement.setInt(4, supplier.getId());

			statement.executeUpdate();
			System.out.println("Supplier updated.");

		} catch (Exception ex) {
			System.out.println("Error in SupplierDAO.updateOne" + ex);
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
}
