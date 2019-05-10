package com.cart.control.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.ProductDAO;
import com.cart.model.Product;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/listProducts")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		List<Product> products = new ProductDAO().list();
		
		req.setAttribute("products", products);
		
		req.getRequestDispatcher("listProducts.jsp").forward(req, res);
	}
	
}
