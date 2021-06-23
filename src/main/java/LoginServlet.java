import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn != null) {
			res.sendRedirect("home");
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn != null) {
			res.sendRedirect("home");
			return;
		}

		try {
			CustomerBean customer = new CustomerBean();
			customer.setUsername(req.getParameter("username"));
			customer.setPassword(req.getParameter("password"));
			customer = CustomerDAO.login(customer);

			if (customer.isValid()) {
				// Logged-in page
				req.getSession().invalidate();
				HttpSession session = req.getSession(true);

				session.setAttribute("loggedIn", true);
				session.setAttribute("id", customer.getId());
				session.setAttribute("username", customer.getUsername());
				session.setAttribute("address", customer.getAddress());
				session.setAttribute("phone", customer.getPhone());
				session.setAttribute("name", customer.getName());

				res.sendRedirect("home");
			} else {
				// Error page
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				req.setAttribute("errorMessage", "Invalid Credentials");
				rd.forward(req, res);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
