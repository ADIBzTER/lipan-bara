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

import com.config.*;
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
			String productActivity = req.getParameter("productActivity");
			String uploadPath = "C:\\Users\\Adib Zaini\\Desktop\\CS230 PART 4\\CSC584 - Enterprise Programming\\Project\\LipanBara\\src\\main\\webapp\\images\\";

			String fileName;
			ProductBean product;
			Part part;

			switch (productActivity) {

			case "addProduct":
				part = req.getPart("image");

				// Upload image to server
				fileName = ImageUploader.upload(uploadPath, part);

				product = new ProductBean();
				product.setName(req.getParameter("name"));
				product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
				product.setPrice(Double.parseDouble(req.getParameter("price")));
				product.setDescription(req.getParameter("description"));
				product.setImageLocation("images/" + fileName);
				product.setSuppId(Integer.parseInt(req.getParameter("supplierId")));

				ProductDAO.addOne(product);

				res.sendRedirect("product");
				System.out.println(fileName + " uploaded");
				break;

			case "deleteProduct":
				ProductDAO.deleteOne(Integer.parseInt(req.getParameter("productId")));
				res.sendRedirect("product");
				break;

			case "updateProduct":
				product = new ProductBean();

				// New image uploaded
				if (req.getPart("image").getSize() != 0) {
					part = req.getPart("image");

					// Upload image to server
					fileName = ImageUploader.upload(uploadPath, part);

					product.setImageLocation("images/" + fileName);
					System.out.println(fileName + " uploaded");
				} else {
					product.setImageLocation(req.getParameter("imageLocation"));
				}

				product.setId(Integer.parseInt(req.getParameter("id")));
				product.setName(req.getParameter("name"));
				product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
				product.setPrice(Double.parseDouble(req.getParameter("price")));
				product.setDescription(req.getParameter("description"));
				product.setSuppId(Integer.parseInt(req.getParameter("supplierId")));

				ProductDAO.updateOne(product);

				res.sendRedirect("product");
				break;

			default:
				res.sendRedirect("product");
				break;
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
