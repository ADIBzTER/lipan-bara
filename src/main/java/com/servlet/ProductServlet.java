package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.*;

import com.dao.*;
import com.bean.*;

@WebServlet("/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		if (req.getParameter("updateProduct") != null) {
			req.setAttribute("supplierList", SupplierDAO.getAll());
			RequestDispatcher rd = req.getRequestDispatcher("product-update.jsp");
			rd.forward(req, res);
		} else {
			try {

				req.setAttribute("productList", ProductDAO.getAll());

				RequestDispatcher rd = req.getRequestDispatcher("product.jsp");
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
			if (req.getParameter("addProduct") != null) {

				String path = "C:\\Users\\Adib Zaini\\Desktop\\CS230 PART 4\\CSC584 - Enterprise Programming\\Project\\LipanBara\\src\\main\\webapp\\images\\";

				// Handle uploaded file
				Part filePart = req.getPart("image");
				String fileName = filePart.getSubmittedFileName();
				for (Part part : req.getParts()) {
					part.write(path + fileName);
				}

				ProductBean product = new ProductBean();
				product.setName(req.getParameter("name"));
				product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
				product.setPrice(Double.parseDouble(req.getParameter("price")));
				product.setDescription(req.getParameter("description"));
				product.setImageLocation("images/" + fileName);
				product.setSuppId(Integer.parseInt(req.getParameter("supplierId")));

				ProductDAO.addOne(product);
				
				res.sendRedirect("product");
			} else {
				res.sendRedirect("product");

//				req.setAttribute("productList", ProductDAO.getAll());
//
//				RequestDispatcher rd = req.getRequestDispatcher("product.jsp");
//				rd.forward(req, res);
			}

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
