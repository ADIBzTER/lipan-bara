package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;

@WebServlet("/purchase")

public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		// ADD
		if (req.getParameter("addProduct") != null) {
			req.setAttribute("supplierList", SupplierDAO.getAll());
			RequestDispatcher rd = req.getRequestDispatcher("product-update.jsp");
			rd.forward(req, res);
		}

		// UPDATE
		else if (req.getParameter("updateProduct") != null) {
			req.setAttribute("supplierList", SupplierDAO.getAll());

			int productId = Integer.parseInt(req.getParameter("productId"));
			req.setAttribute("product", ProductDAO.getOne(productId));

			RequestDispatcher rd = req.getRequestDispatcher("product-update.jsp");
			rd.forward(req, res);
		}

		// DISPLAY
		else {
			try {
				req.setAttribute("purchaseList", PurchaseDAO.getAll());

				RequestDispatcher rd = req.getRequestDispatcher("purchase.jsp");
				rd.forward(req, res);

			} catch (Throwable theException) {
				System.out.println(theException);
			}
		}
	}
}
