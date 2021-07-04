package com.dao;

import java.sql.*;
import java.util.*;

import com.config.*;
import com.bean.*;

public class PurchaseDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Get all purchases
	public static List<PurchaseBean> getAll() {

		List<PurchaseBean> purchaseList = new LinkedList<>();
		String sql = "SELECT * FROM purchases pur JOIN customers c ON pur.cust_id = c.cust_id "
				+ "JOIN products pro ON pur.prod_id = pro.prod_id ORDER BY purc_id;";
		Statement statement = null;

		// Trace process
		System.out.println("in PurchaseDAO.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Purchase
				PurchaseBean purchase = new PurchaseBean();
				purchase.setId(resultSet.getInt("purc_id"));
				purchase.setDate(resultSet.getDate("purc_date").toString());
				purchase.setShipping(resultSet.getString("purc_shipping"));
				purchase.setQuantity(resultSet.getInt("purc_quantity"));
				purchase.setPrice(resultSet.getDouble("purc_price"));

				// Customer
				CustomerBean customer = purchase.getCustomer();
				customer.setId(resultSet.getInt("cust_id"));
				customer.setName(resultSet.getString("cust_name"));
				customer.setUsername(resultSet.getString("cust_username"));
				customer.setPassword(resultSet.getString("cust_password"));
				customer.setAddress(resultSet.getString("cust_address"));
				customer.setPhone(resultSet.getString("cust_phone"));

				// Product
				ProductBean product = purchase.getProduct();
				product.setId(resultSet.getInt("prod_id"));
				product.setName(resultSet.getString("prod_name"));
				product.setQuantity(resultSet.getInt("prod_quantity"));
				product.setPrice(resultSet.getDouble("prod_price"));
				product.setDescription(resultSet.getString("prod_description"));
				product.setImageLocation(resultSet.getString("prod_image_location"));
				product.setSuppId(resultSet.getInt("supp_id"));

				purchaseList.add(purchase);
			}
		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.getAll: " + ex);
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
		return purchaseList;
	}

	// Get all purchases by month
	public static List<PurchaseBean> getAllByMonth() {

		List<PurchaseBean> purchaseList = new LinkedList<>();
		String sql = "SELECT DATE_FORMAT(purc_date, '%M %Y') AS month, SUM(purc_price) AS sum "
				+ "FROM purchases GROUP BY month;";
		Statement statement = null;

		// Trace process
		System.out.println("in PurchaseDAO.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Purchase
				PurchaseBean purchase = new PurchaseBean();
				purchase.setDate(resultSet.getString("month"));
				purchase.setPrice(resultSet.getDouble("sum"));

				purchaseList.add(purchase);
			}
		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.getAllByMonth: " + ex);
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
		return purchaseList;
	}

	// Get only one purchase
	public static PurchaseBean getOne(int purchaseId) {

		// Preparing some objects/variable
		PurchaseBean purchase = new PurchaseBean();
		String sql = "SELECT * from purchases WHERE purc_id=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("In PurchaseDAO.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, purchaseId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Product
				purchase.setId(resultSet.getInt("purc_id"));
				purchase.setDate(resultSet.getDate("purc_date").toString());
				purchase.setShipping(resultSet.getString("purc_shipping"));
				purchase.setQuantity(resultSet.getInt("purc_quantity"));
				purchase.setPrice(resultSet.getDouble("purc_price"));

				// Customer
				CustomerBean customer = purchase.getCustomer();
				customer.setId(resultSet.getInt("cust_id"));
				customer.setName(resultSet.getString("cust_name"));
				customer.setUsername(resultSet.getString("cust_username"));
				customer.setAddress(resultSet.getString("cust_address"));
				customer.setPhone(resultSet.getString("cust_phone"));

				// Product
				ProductBean product = purchase.getProduct();
				product.setId(resultSet.getInt("prod_id"));
				product.setName(resultSet.getString("prod_name"));
				product.setQuantity(resultSet.getInt("prod_quantity"));
				product.setPrice(resultSet.getDouble("prod_price"));
				product.setDescription(resultSet.getString("prod_description"));
				product.setImageLocation(resultSet.getString("prod_image_location"));
				product.setSuppId(resultSet.getInt("supp_id"));
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
		return purchase;
	}

	// add one purchase
	public static void addOne(PurchaseBean purchase) {

		// Preparing some objects/variable
		String sql = "INSERT INTO purchases (purc_date, purc_shipping, purc_quantity, purc_price,cust_id,prod_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("In PurchaseDAO.addOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setString(1, purchase.getDate());
			statement.setString(2, purchase.getShipping());
			statement.setInt(3, purchase.getQuantity());
			statement.setDouble(4, purchase.getPrice());
			statement.setInt(5, purchase.getCustId());
			statement.setInt(6, purchase.getProdId());

			statement.executeUpdate();
			System.out.println("New Purchase added to database.");

		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.addOne " + ex);
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
