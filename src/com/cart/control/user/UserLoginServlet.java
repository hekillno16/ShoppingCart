package com.cart.control.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.UserDAO;
import com.cart.model.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		User user = new UserDAO().get(name, password);

		if (user != null) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("listProducts");
		} else {
			response.getWriter().println("<script type=\"text/javascript\">");
			response.getWriter().println("alert('User or password incorrect');");
			response.getWriter().println("location='login.jsp';");
			response.getWriter().println("</script>");
//			response.sendRedirect("login.jsp");
		}
	}

}
