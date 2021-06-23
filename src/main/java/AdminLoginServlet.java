import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("admin-login.jsp");
		rd.forward(req, res);
	}

	// Login attempt
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
		try {
			AdminBean admin = new AdminBean();
			admin.setUsername(req.getParameter("username"));
			admin.setPassword(req.getParameter("password"));
			admin = AdminDAO.login(admin);

			if (admin.isValid()) {
				// Logged-in page
				req.getSession().invalidate();
				HttpSession session = req.getSession(true);

				session.setAttribute("id", admin.getId());
				session.setAttribute("username", admin.getUsername());
				session.setAttribute("adminLoggedIn", true);

				RequestDispatcher rd = req.getRequestDispatcher("product");
				rd.forward(req, res);
			} else {
				// Error page
				RequestDispatcher rd = req.getRequestDispatcher("admin-login.jsp");
				req.setAttribute("errorMessage", "Invalid Credentials");
				rd.forward(req, res);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
