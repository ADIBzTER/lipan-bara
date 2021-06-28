package com.dao;

import java.sql.*;
import java.util.*;

import com.config.*;
import com.bean.*;

public class ProductDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static List<ProductBean> getAll() {
		// Preparing some objects/variable
		List<ProductBean> productList = new LinkedList<>();
		String sql = "SELECT * FROM products p JOIN suppliers s ON (p.supp_id = s.supp_id) ORDER BY prod_id;";

		Statement statement = null;

		// Trace process
		System.out.println("in ProductBean.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Product
				ProductBean product = new ProductBean();
				product.setId(resultSet.getInt("prod_id"));
				product.setName(resultSet.getString("prod_name"));
				product.setQuantity(resultSet.getInt("prod_quantity"));
				product.setPrice(resultSet.getDouble("prod_price"));
				product.setDescription(resultSet.getString("prod_description"));
				product.setImageLocation(resultSet.getString("prod_image_location"));
				product.setSuppId(resultSet.getInt("supp_id"));

				// Supplier
				SupplierBean supplier = product.getSupplier();
				supplier.setId(resultSet.getInt("supp_id"));
				supplier.setName(resultSet.getString("supp_name"));
				supplier.setAddress(resultSet.getString("supp_address"));
				supplier.setPhone(resultSet.getString("supp_phone"));

				productList.add(product);
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
		return productList;
	}

	// Get only one product
	public static ProductBean getOne(int productId) {

		// Preparing some objects/variable
		ProductBean product = new ProductBean();
		String sql = "SELECT * from products WHERE prod_id=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("in ProductBean.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, productId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Product
				product.setId(resultSet.getInt("prod_id"));
				product.setName(resultSet.getString("prod_name"));
				product.setQuantity(resultSet.getInt("prod_quantity"));
				product.setPrice(resultSet.getDouble("prod_price"));
				product.setDescription(resultSet.getString("prod_description"));
				product.setImageLocation(resultSet.getString("prod_image_location"));
				product.setSuppId(resultSet.getInt("supp_id"));

			}
		} catch (Exception ex) {
			System.out.println("Error in ProductDAO.getOne " + ex);
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
		return product;
	}

	// Add one product
	public static void addOne(ProductBean product) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String name = product.getName();
		int quantity = product.getQuantity();
		double price = product.getPrice();
		String description = product.getDescription();
		String imageLocation = product.getImageLocation();
		int suppId = product.getSuppId();

		// Prepared statement
		String sql = "INSERT INTO products (prod_name, prod_quantity, prod_price, prod_description, prod_image_location, supp_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";

		// Used to trace the process
		System.out.println("in ProductDAO.addOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, name);
			statement.setInt(2, quantity);
			statement.setDouble(3, price);
			statement.setString(4, description);
			statement.setString(5, imageLocation);
			statement.setInt(6, suppId);

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

	public static void deleteOne(int productId) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "DELETE FROM products WHERE prod_id = ?";

		// Used to trace the process
		System.out.println("in ProductDAO.deleteOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, productId);

			statement.executeUpdate();
			System.out.println("Product remove from database.");

		} catch (Exception ex) {
			System.out.println("Error in Product.DAO.deleteOne" + ex);
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

	public static void updateOne(ProductBean product) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "UPDATE products "
				+ "SET prod_name = ?, prod_quantity = ?, prod_price = ?, prod_description = ?, prod_image_location = ?, supp_id = ? "
				+ " WHERE prod_id = ?";

		// Used to trace the process
		System.out.println("in ProductDAO.updateOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setDouble(3, product.getPrice());
			statement.setString(4, product.getDescription());
			statement.setString(5, product.getImageLocation());
			statement.setInt(6, product.getSuppId());
			statement.setInt(7, product.getId());

			statement.executeUpdate();
			System.out.println("Product updated.");

		} catch (Exception ex) {
			System.out.println("Error in Product.DAO.deleteOne" + ex);
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
