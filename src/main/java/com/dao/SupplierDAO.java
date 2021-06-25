package com.dao;

import java.sql.*;
import java.util.*;

import com.connection.*;
import com.bean.*;

public class SupplierDAO {
	static Connection currentCon = null;
	static ResultSet resultSet = null;

	public static List<SupplierBean> getAll() {

		// Preparing some objects/variable
		List<SupplierBean> supplierList = new LinkedList<>();
		String sql = "select * from suppliers;";

		Statement statement = null;

		// Trace process
		System.out.println("in SupplierBean.getAll");

		try {
			// Connect to DB
			currentCon = ConnectionManager.getConnection();

			statement = currentCon.createStatement();

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return supplierList;
	}
}
