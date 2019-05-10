package com.cart.control.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.ProductDAO;
import com.cart.model.Product;

/**
 * Servlet implementation class ProductAddServlet
 */
@WebServlet("/addProduct")
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("add product - name: " + request.getParameter("name")
						+" | price: " + request.getParameter("price")); 
		
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		
		Product p = new Product(name, price);				
		
		new ProductDAO().add(p);
		
		response.getWriter().println(p.getId());
	}

}
