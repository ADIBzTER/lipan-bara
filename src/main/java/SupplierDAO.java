import java.sql.*;
import java.util.*;

public class SupplierDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static List<SupplierBean> getAll() {
		// preparing some objects/variable
		List<SupplierBean> supplierList = new LinkedList<>();
		String sql = "select * from suppliers";

		Statement stmt = null;
		// trace process
		System.out.println("in SupplierBean.getAll");

		try {
			// connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(sql);

			// iterate over the ResultSet, add row into object and object into list
			while (rs.next()) {
				SupplierBean supplier = new SupplierBean();
				supplier.setId(rs.getInt(1));
				supplier.setName(rs.getString(2));
				supplier.setAddress(rs.getString(3));
				supplier.setPhone(rs.getString(4));
				supplierList.add(supplier);

				System.out.println(supplierList);
			}
		} catch (Exception ex) {
			System.out.println("SupplierDAO failed: " + ex);
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
		return supplierList;
	}
}
