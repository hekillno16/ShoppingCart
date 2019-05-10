package com.cart.control.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.ProductDAO;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/deleteProduct")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("delete product - id: " + request.getParameter("id"));
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		new ProductDAO().delete(id);
		
		response.getWriter().println("Deleted!");
	}

}
