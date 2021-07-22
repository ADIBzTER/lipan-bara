package com.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.*;
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
				req.setAttribute("purchaseList", PurchaseDAO.getAllByMonth());

				RequestDispatcher rd = req.getRequestDispatcher("purchase.jsp");
				rd.forward(req, res);

			} catch (Throwable theException) {
				System.out.println(theException);
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// user not logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		try {
			List<Integer> cartIds = new LinkedList<>();
			for (String cartId : req.getParameterValues("cartId")) {
				cartIds.add(Integer.parseInt(cartId));
			}

			List<CartBean> cartList = CartDAO.getUserCart(Integer.parseInt(req.getParameter("userId")));

			List<PurchaseBean> purchaseList = new LinkedList<>();

			for (CartBean cart : cartList) {
				PurchaseBean purchase = new PurchaseBean();

				purchase.setDate(java.time.LocalDate.now().toString());
				purchase.setShipping("Lipan Express");
				purchase.setQuantity(1);
				purchase.setPrice(cart.getProduct().getPrice());
				purchase.setCustId(cart.getCustId());
				purchase.setProdId(cart.getProdId());

				CustomerBean customer = CustomerDAO.getOne(cart.getCustId());
				purchase.setCustomer(customer);

				PurchaseDAO.addOne(purchase);

				ProductBean product = ProductDAO.getOne(cart.getProdId());
				product.setQuantity(product.getQuantity() - 1);
				ProductDAO.updateOne(product);
				purchase.setProduct(product);

				CartDAO.removeFromCart(cart.getId());

				purchaseList.add(purchase);
			}

			req.setAttribute("purchaseList", purchaseList);
			RequestDispatcher rd = req.getRequestDispatcher("receipt");
			rd.forward(req, res);
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
