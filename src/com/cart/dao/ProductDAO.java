package com.cart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cart.model.Product;
import com.cart.util.ConnectionPool;
import com.cart.util.HibernateUtil;
import com.cart.util.JdbcUtility;

public class ProductDAO implements IDao<Product> {	
	
	private final String TABLE_NAME = "product";

	@Override
	public void add(Product p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();		
		
		session.save(p);
		
		transaction.commit();
		session.close();		
	}

	@Override
	public void update(Product p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Product target = (Product) session.get(Product.class, p.getId());		
		target.setName(p.getName());
		target.setPrice(p.getPrice());
		session.update(target);
		
		transaction.commit();
		session.close();		
	}

	@Override
	public void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Product target = (Product) session.get(Product.class, id);		
		session.delete(target);
		
		transaction.commit();
		session.close();
	}

	@Override
	public Product get(int id) {
		Product product = null; 
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID=?";
		
		try{
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				product = new Product();
				product.setId(id);
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
			}
			
			JdbcUtility.closeAll(con, ps, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}

	@Override
	public List<Product> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public List<Product> list(int offset, int count) {
		List<Product> result = new ArrayList<>();

		String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY id LIMIT ?,?";

		try {
			
			Connection con = ConnectionPool.getInstance().getConnection(); 
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, offset);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");

				Product pd = new Product(name, price);
				pd.setId(id);

				result.add(pd);
			}
			
			JdbcUtility.closeAll(con, ps, rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
