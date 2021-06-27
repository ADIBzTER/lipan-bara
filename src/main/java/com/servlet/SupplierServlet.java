package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;

@WebServlet("/supplier")
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(false).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		try {

			req.setAttribute("supplierList", SupplierDAO.getAll());

			RequestDispatcher rd = req.getRequestDispatcher("supplier.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
