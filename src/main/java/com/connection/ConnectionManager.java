package com.connection;

import java.sql.*;

public class ConnectionManager {
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/lipan_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static Connection connection = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("connected to driver");
			try {
				connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				System.out.println("Connected to lipan_db");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return connection;
	}
}