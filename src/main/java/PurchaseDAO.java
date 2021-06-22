import java.sql.*;
import java.util.*;

public class PurchaseDAO {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static List<PurchaseBean> getAll() {

		List<PurchaseBean> purchaseList = new LinkedList<>();
		String sql = "SELECT * FROM purchases pur " + "JOIN customers c " + "ON pur.cust_id = c.cust_id "
				+ "JOIN products pro " + "ON pur.prod_id = pro.prod_id";
		Statement statement = null;

		// Trace process
		System.out.println("in PurchaseDAO.getAll");

		try {
			// connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				PurchaseBean purchase = new PurchaseBean();

				purchase.setId(resultSet.getInt("purc_id"));
				purchase.setDate(resultSet.getString("purc_date"));
				purchase.setShipping(resultSet.getString("purc_shipping"));
				purchase.setQuantity(resultSet.getInt("purc_quantity"));

//				purchase.setSupplier(new SupplierBean(resultSet.getInt(9), resultSet.getString(10),
//						resultSet.getString(11), resultSet.getString(12)));

				purchaseList.add(purchase);

				System.out.println(purchaseList);
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
		return purchaseList;
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
