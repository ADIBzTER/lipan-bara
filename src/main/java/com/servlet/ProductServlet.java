package com.servlet;

import java.util.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.*;
import com.dao.*;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(false).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		try {

			req.setAttribute("productList", ProductDAO.getAll());

			RequestDispatcher rd = req.getRequestDispatcher("product.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

	// After Login
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(false).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		try {

			req.setAttribute("productList", ProductDAO.getAll());

			RequestDispatcher rd = req.getRequestDispatcher("product.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
