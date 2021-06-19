import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
		try {
			CustomerBean customer = new CustomerBean();
			customer.setUsername(req.getParameter("username"));
			customer.setPassword(req.getParameter("password"));
			customer = CustomerDAO.login(customer);

			if (customer.isValid()) {

				// Logged-in page
				HttpSession session = req.getSession(true);
				session.setAttribute("id", customer.getId());
				session.setAttribute("username", customer.getUsername());
				session.setAttribute("address", customer.getAddress());
				session.setAttribute("phone", customer.getPhone());
//				req.setAttribute("userList", UserDAO.getAll());

				RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
				rd.forward(req, res);
			} else
				res.sendRedirect("invalidLogin.jsp"); // error page
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
