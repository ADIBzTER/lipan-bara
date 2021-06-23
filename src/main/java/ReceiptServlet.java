import java.util.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/receipt")
public class ReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
		Object loggedIn = req.getSession(false).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("login");
			return;
		}

		try {
			// Update purchases table
//			int productId = Integer.parseInt(req.getParameter("prodctId"));


//			req.setAttribute("supplierList", SupplierDAO.getAll());

			RequestDispatcher rd = req.getRequestDispatcher("receipt.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
		Object loggedIn = req.getSession(false).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("login");
			return;
		}

		try {
//			int productId = Integer.parseInt(req.getParameter("prodctId"));


//			req.setAttribute("supplierList", SupplierDAO.getAll());

			RequestDispatcher rd = req.getRequestDispatcher("receipt.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
