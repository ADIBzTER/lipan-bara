import java.sql.*;
import java.util.*;

public class CartDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static List<CartBean> getUserCart(int userId) {

		List<CartBean> productList = new ArrayList<>();
		String sql = "SELECT * " + "FROM carts ca " + "JOIN customers cu " + "ON ca.cust_id = cu.cust_id "
				+ "JOIN products p " + "ON ca.prod_id = p.prod_id " + "WHERE ca.cust_id=?";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("in CartDAO.getCart");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				CartBean cart = new CartBean();

				cart.setId(resultSet.getInt("cart_id"));
				cart.setCustId(resultSet.getInt("cust_id"));
				cart.setProdId(resultSet.getInt("prod_id"));

				CustomerBean customer = cart.getCustomer();
				customer.setId(resultSet.getInt(1));
				customer.setPhone(resultSet.getString("cust_phone"));
				customer.setAddress(resultSet.getString("cust_address"));

				ProductBean product = cart.getProduct();
				product.setId(resultSet.getInt("prod_id"));
				product.setName(resultSet.getString("prod_name"));
				product.setQuantity(resultSet.getInt("prod_quantity"));
				product.setPrice(resultSet.getDouble("prod_price"));
				product.setDescription(resultSet.getString("prod_description"));
				product.setImageLocation(resultSet.getString("prod_image_location"));

				productList.add(cart);

			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} // some exception handling
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

	public static void addToCart(CartBean cart) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		int custId = cart.getCustId();
		int prodId = cart.getProdId();

		// Prepared statement
		String sql = "INSERT INTO carts VALUES (null, ?, ?)";

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
			System.out.println("Error in CartDAO " + ex);
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
