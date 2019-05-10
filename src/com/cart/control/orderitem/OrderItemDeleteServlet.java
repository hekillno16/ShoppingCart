package com.cart.control.orderitem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.OrderItem;

/**
 * Servlet implementation class OrderItemDeleteServlet
 */
@WebServlet("/deleteOrderItem")
public class OrderItemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderItemDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OrderItem> oilst = (List<OrderItem>) request.getSession().getAttribute("oilst");
		
		int id = Integer.parseInt(request.getParameter("id"));
		int num = Integer.parseInt(request.getParameter("num"));
		
//		System.out.println("DeleteOrderItem: ID - " + id + " NUM - " + num);
			
		boolean found = false;
		for (int i = 0; i < oilst.size(); i++) {			
			if (oilst.get(i).getId() == id) {
				found = true;
				
				num = num <= oilst.get(i).getNum() ? num : oilst.get(i).getNum();
				num = oilst.get(i).getNum() - num;
				
				if (num > 0)
					oilst.get(i).setNum(num);
				else
					oilst.remove(i);
			}
		}
		
		request.getSession().setAttribute("oilst", oilst);
		
		response.sendRedirect("listOrderItem");
	}

}
