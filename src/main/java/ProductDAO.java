import java.sql.*;
import java.util.*;

public class ProductDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static List<ProductBean> getAll() {
		// preparing some objects/variable
		List<ProductBean> productList = new LinkedList<>();
		String sql = "select * from products";

		Statement stmt = null;
		// trace process
		System.out.println("in ProductBean.getAll");

		try {
			// connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(sql);

			// iterate over the ResultSet, add row into object and object into list
			while (rs.next()) {
				ProductBean product = new ProductBean();
				product.setProdId(rs.getInt(1));
				product.setProdName(rs.getString(2));
				product.setProdQuantity(rs.getInt(3));
				product.setProdPrice(rs.getDouble(4));
				product.setProdDescription(rs.getString(5));
				product.setProdImageLocation(rs.getString(6));
				product.setSuppId(rs.getInt(7));
				product.setAdId(rs.getInt(8));
				productList.add(product);

				System.out.println(productList);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} // some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return productList;
	}
}
