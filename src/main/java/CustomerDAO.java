import java.sql.*;
import java.util.*;

public class CustomerDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static CustomerBean login(CustomerBean bean) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String username = bean.getUsername();
		String password = bean.getPassword();

		// Prepared statement
		String searchQuery = "SELECT * FROM customers WHERE cust_username=? AND cust_password=?";

		// used to trace the process
		System.out.println("in UserBean.login");
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
				bean.setValid(false);
			}
			// If user exists
			else if (more) {
				int id = resultSet.getInt("cust_id");
				String address = resultSet.getString("cust_address");
				String phone = resultSet.getString("cust_phone");

				System.out.println("Welcome " + username);
				bean.setId(id);
				bean.setAddress(address);
				bean.setPhone(phone);
				bean.setValid(true);
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
		return bean;
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
