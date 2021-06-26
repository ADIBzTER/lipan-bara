package com.dao;

import java.sql.*;
import java.util.*;

import com.config.*;
import com.bean.*;

public class CartDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Get all products from user cart
	public static List<CartBean> getUserCart(int userId) {

		List<CartBean> cartList = new ArrayList<>();
		String sql = "SELECT * FROM carts ca JOIN customers cu ON ca.cust_id = cu.cust_id "
				+ "JOIN products p ON ca.prod_id = p.prod_id WHERE ca.cust_id=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("in CartDAO.getCart");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, userId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Cart
				CartBean cart = new CartBean();
				cart.setId(resultSet.getInt("cart_id"));
				cart.setCustId(resultSet.getInt("cust_id"));
				cart.setProdId(resultSet.getInt("prod_id"));

				// Customer
				CustomerBean customer = cart.getCustomer();
				customer.setId(resultSet.getInt("cust_id"));
				customer.setPhone(resultSet.getString("cust_phone"));
				customer.setAddress(resultSet.getString("cust_address"));

				// Product
				ProductBean product = cart.getProduct();
				product.setId(resultSet.getInt("prod_id"));
				product.setName(resultSet.getString("prod_name"));
				product.setQuantity(resultSet.getInt("prod_quantity"));
				product.setPrice(resultSet.getDouble("prod_price"));
				product.setDescription(resultSet.getString("prod_description"));
				product.setImageLocation(resultSet.getString("prod_image_location"));

				cartList.add(cart);

			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
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
		return cartList;
	}

	// Add one product into user cart
	public static void addToCart(CartBean cart) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		int custId = cart.getCustId();
		int prodId = cart.getProdId();

		// Prepared statement
		String sql = "INSERT INTO carts (cust_id, prod_id) VALUES (?, ?);";

		// Used to trace the process
		System.out.println("in CartDAO.addToCart");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, custId);
			statement.setInt(2, prodId);

			statement.executeUpdate();
			System.out.println("Product added to database.");

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

	// Remove one product into user cart
	public static void removeFromCart(int cartId) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "DELETE FROM carts WHERE cart_id = ?";

		// Used to trace the process
		System.out.println("in CartDAO.removeFromCart");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, cartId);

			statement.executeUpdate();
			System.out.println("Product remove from cart.");

		} catch (Exception ex) {
			System.out.println("Error in CartDAO.removeFromcart " + ex);
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
