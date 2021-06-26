package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.*;
import com.dao.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn != null) {
			res.sendRedirect("home");
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
		rd.forward(req, res);
	}

	// Register attempt
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
		try {

			// User logged in
			Object loggedIn = req.getSession(true).getAttribute("loggedIn");
			if (loggedIn != null) {
				res.sendRedirect("home");
				return;
			}

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

				// Insert customer into database
				CustomerDAO.addCustomer(customer);

				// Login after register
				RequestDispatcher rd = req.getRequestDispatcher("login");
				rd.forward(req, res);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
