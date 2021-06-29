package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;
import com.bean.*;

@WebServlet("/supplier")
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		// ADD
		if (req.getParameter("addSupplier") != null) {
			RequestDispatcher rd = req.getRequestDispatcher("supplier-update.jsp");
			rd.forward(req, res);
		}

		// UPDATE
		else if (req.getParameter("updateSupplier") != null) {
			int supplierId = Integer.parseInt(req.getParameter("supplierId"));
			req.setAttribute("supplier", SupplierDAO.getOne(supplierId));

			RequestDispatcher rd = req.getRequestDispatcher("supplier-update.jsp");
			rd.forward(req, res);
		}

		// DISPLAY
		else {
			try {
				req.setAttribute("supplierList", SupplierDAO.getAll());

				RequestDispatcher rd = req.getRequestDispatcher("supplier.jsp");
				rd.forward(req, res);

			} catch (Throwable theException) {
				System.out.println(theException);
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		try {
			String supplierActivity = req.getParameter("supplierActivity");
			SupplierBean supplier;

			switch (supplierActivity) {

			case "addSupplier":

				supplier = new SupplierBean();
				supplier.setName(req.getParameter("name"));
				supplier.setAddress(req.getParameter("address"));
				supplier.setPhone(req.getParameter("phone"));

				SupplierDAO.addOne(supplier);

				res.sendRedirect("supplier");
				break;

			case "deleteSupplier":
				SupplierDAO.deleteOne(Integer.parseInt(req.getParameter("supplierId")));
				res.sendRedirect("supplier");
				break;

			case "updateSupplier":
				supplier = new SupplierBean();

				supplier.setId(Integer.parseInt(req.getParameter("id")));
				supplier.setName(req.getParameter("name"));
				supplier.setAddress(req.getParameter("address"));
				supplier.setPhone(req.getParameter("phone"));

				SupplierDAO.updateOne(supplier);

				res.sendRedirect("supplier");
				break;

			default:
				res.sendRedirect("supplier");
				break;
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
