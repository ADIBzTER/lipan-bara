import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
		rd.forward(req, res);
	}

	// Register attempt
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
		try {
			// Username Not Available
			if (!CustomerDAO.isUsernameAvailable(req.getParameter("username"))) {
				// Error page
				RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
				req.setAttribute("errorMessage", "Username already exists");
				rd.forward(req, res);
			}
			// Username available
			else {

				CustomerBean customer = new CustomerBean();
				customer.setName(req.getParameter("name"));
				customer.setUsername(req.getParameter("username"));
				customer.setPassword(req.getParameter("password"));
				customer.setAddress(req.getParameter("address"));
				customer.setPhone(req.getParameter("phone"));

				CustomerDAO.addCustomer(customer);
				customer = CustomerDAO.login(customer);

				if (customer.isValid()) {
					// Logged-in page
					HttpSession session = req.getSession(true);
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
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
