import java.sql.*;
import java.util.*;

public class CustomerDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static CustomerBean login(CustomerBean customer) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String username = customer.getUsername();
		String password = customer.getPassword();

		// Prepared statement
		String searchQuery = "SELECT * FROM customers WHERE cust_username=? AND cust_password=?";

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
				String address = resultSet.getString("cust_address");
				String phone = resultSet.getString("cust_phone");
				String name = resultSet.getString("cust_name");

				System.out.println("Welcome " + username);
				customer.setId(id);
				customer.setAddress(address);
				customer.setPhone(phone);
				customer.setName(name);
				customer.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} // Some exception handling
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

	public static CustomerBean addCustomer(CustomerBean customer) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String name = customer.getName();
		String username = customer.getUsername();
		String password = customer.getPassword();
		String address = customer.getAddress();
		String phone = customer.getPhone();

		// Prepared statement
		String sql = "INSERT INTO customers VALUES (null, ?, ?, ?, ?, ?)";

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
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} // Some exception handling
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

	public static boolean isUsernameAvailable(String username) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		boolean isAvailable = false;

		// Prepared statement
		String sql = "SELECT cust_username FROM customers WHERE cust_username=?";

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

//	public static List<CustomerBean> getAll() {
//		// Preparing some objects/variable
//		List<CustomerBean> customerList = new LinkedList<>();
//		String sql = "select firstname, lastname from user";
//
//		Statement stmt = null;
//		// trace process
//		System.out.println("in UserBean.getAll");
//		try {
//			// connect to DB
//			currentCon = ConnectionManager.getConnection();
//			stmt = currentCon.createStatement();
//			resultSet = stmt.executeQuery(sql);
//
//			// iterate over the ResultSet, add row into object and object into list
//			while (resultSet.next()) {
//				UserBean ub = new UserBean();
//				ub.setFirstName(resultSet.getString(1));
//				ub.setLastName(resultSet.getString(2));
//				customerList.add(ub);
//
//				System.out.println(customerList);
//			}
//		} catch (Exception ex) {
//			System.out.println("Log In failed: An Exception has occurred! " + ex);
//		} // some exception handling
//		finally {
//			if (resultSet != null) {
//				try {
//					resultSet.close();
//				} catch (Exception e) {
//				}
//				resultSet = null;
//			}
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (Exception e) {
//				}
//				stmt = null;
//			}
//			if (currentCon != null) {
//				try {
//					currentCon.close();
//				} catch (Exception e) {
//				}
//				currentCon = null;
//			}
//		}
//		return customerList;
//	}
}
