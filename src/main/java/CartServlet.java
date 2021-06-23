import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Display products in cart
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("login");
			return;
		}

		try {
			int userId = (int) req.getSession(false).getAttribute("id");

			req.setAttribute("productList", CartDAO.getUserCart(userId));

			RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

	// Insert product to cart
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("login");
			return;
		}

		try {
//			req.setAttribute("product", ProductDAO.getOne(productId));

			int customerId = (int) req.getSession().getAttribute("id");
			int productId = Integer.parseInt(req.getParameter("productId"));

			CartBean cart = new CartBean();
			cart.setCustId(customerId);
			cart.setProdId(productId);

			// Insert product to cart
			CartDAO.addToCart(cart);

			res.sendRedirect("cart");

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
