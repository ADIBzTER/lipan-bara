package com.dao;

import java.sql.*;
import java.util.*;

import com.connection.*;
import com.bean.*;

public class ProductDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static List<ProductBean> getAll() {
		// Preparing some objects/variable
		List<ProductBean> productList = new LinkedList<>();
		String sql = "SELECT * FROM products p JOIN suppliers s ON (p.supp_id = s.supp_id);";

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
}
