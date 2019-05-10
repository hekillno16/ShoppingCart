package com.cart.control.orderitem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.ProductDAO;
import com.cart.model.OrderItem;
import com.cart.model.Product;

/**
 * Servlet implementation class OrderItemAddServlet
 */
@WebServlet("/addOrderItem")
public class OrderItemAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderItemAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("test: " + request.getParameter("num") + " | " + request.getParameter("pid"));
		
		int num = Integer.parseInt(request.getParameter("num"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		Product product = new ProductDAO().get(pid);
		
		OrderItem oi = new OrderItem();
		oi.setNum(num);
		oi.setProduct(product);
		
		List<OrderItem> oilst = (List<OrderItem>) request.getSession().getAttribute("oilst");
		
		if (oilst == null) {
			oilst = new ArrayList<>();
			request.getSession().setAttribute("oilst", oilst);
		}
		
		//if bought the same item again, update the items numbers
		boolean found = false;
		for (OrderItem curr: oilst) {
			if (curr.getProduct().getId() == oi.getProduct().getId()) {
				curr.setNum(curr.getNum() + oi.getNum());
				found = true;
				break;
			}
		}		
		
		if (!found) {
			oi.setId(oilst.size() + 1);
			oilst.add(oi);
		}
		
		response.sendRedirect("listOrderItem");
	}

}
