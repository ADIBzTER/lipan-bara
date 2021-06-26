package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Object adminLoggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		Object userLoggedIn = req.getSession(true).getAttribute("loggedIn");
		if (adminLoggedIn != null) {
			req.getSession(true).invalidate();
			res.sendRedirect("adminLogin");
		} else if (userLoggedIn != null) {
			req.getSession(true).invalidate();
			res.sendRedirect("login");
		} else {
			res.sendRedirect("home");
		}
	}
}
