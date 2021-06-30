package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/receipt")
public class ReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("login");
			return;
		}

		try {

			RequestDispatcher rd = req.getRequestDispatcher("receipt.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
