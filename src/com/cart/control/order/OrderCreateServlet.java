package com.cart.control.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.OrderDAO;
import com.cart.dao.OrderItemDAO;
import com.cart.model.Order;
import com.cart.model.OrderItem;
import com.cart.model.User;

/**
 * Servlet implementation class OrderCreateServlet
 */
@WebServlet("/createOrder")
public class OrderCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		Order order = new Order();
		order.setUser(user);
		
		new OrderDAO().add(order);		//order's id got set
		
		List<OrderItem> oilst = (List<OrderItem>) request.getSession().getAttribute("oilst");
		for (OrderItem oi: oilst) {
			oi.setOrder(order);
			new OrderItemDAO().add(oi);
		}
		
		oilst.clear();
		
		response.getWriter().println("<h1>Your order has been made!</h1>");
		
			
	}

}
