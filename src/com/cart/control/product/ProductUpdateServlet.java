package com.cart.control.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.ProductDAO;
import com.cart.model.Product;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/updateProduct")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("update p: "); 
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("name"));
//		System.out.println(request.getParameter("price"));
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		float price = Float.parseFloat(request.getParameter("price"));
		
		Product p = new Product(name, price);
		p.setId(id);
		
		new ProductDAO().update(p);
		
		JSONObject json = new JSONObject();
		json.put("product", JSONObject.fromObject(p));				
		
		
		response.getWriter().println(json);
	}

}
