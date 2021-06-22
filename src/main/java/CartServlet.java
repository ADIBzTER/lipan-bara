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

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int userId = (Integer) req.getSession(false).getAttribute("id");

			req.setAttribute("carts", CartDAO.getUserCart(userId));

			RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// No session
		if (req.getSession(false) == null) {
			res.sendRedirect("login");
			return;
		} else {
			try {
				int productId = Integer.parseInt(req.getParameter("productId"));
				req.setAttribute("product", ProductDAO.getOne(productId));

				RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
				rd.forward(req, res);

			} catch (Throwable theException) {
				System.out.println(theException);
			}
		}
	}

}
