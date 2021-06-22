import java.sql.*;
import java.util.*;

public class ProductDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static List<ProductBean> getAll() {
		// preparing some objects/variable
		List<ProductBean> productList = new LinkedList<>();
		String sql = "SELECT * FROM products p JOIN suppliers s ON (p.supp_id = s.supp_id)";

		Statement statement = null;
		// Trace process
		System.out.println("in ProductBean.getAll");

		try {
			// connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				ProductBean product = new ProductBean();

				product.setId(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setQuantity(resultSet.getInt(3));
				product.setPrice(resultSet.getDouble(4));
				product.setDescription(resultSet.getString(5));
				product.setImageLocation(resultSet.getString(6));
				product.setSuppId(resultSet.getInt(7));
				product.setAdId(resultSet.getInt(8));

				product.setSupplier(new SupplierBean(resultSet.getInt(9), resultSet.getString(10),
						resultSet.getString(11), resultSet.getString(12)));

				productList.add(product);

				System.out.println(productList);
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

	public static ProductBean getOne(int productId) {
		// Preparing some objects/variable
		ProductBean product = new ProductBean();
		String sql = "SELECT * from products WHERE prod_id=?";

		PreparedStatement statement = null;
		// trace process
		System.out.println("in ProductBean.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, productId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				product = new ProductBean();

				product.setId(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setQuantity(resultSet.getInt(3));
				product.setPrice(resultSet.getDouble(4));
				product.setDescription(resultSet.getString(5));
				product.setImageLocation(resultSet.getString(6));
				product.setSuppId(resultSet.getInt(7));
				product.setAdId(resultSet.getInt(8));

				System.out.println(product);
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