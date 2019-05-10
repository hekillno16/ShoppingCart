package com.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cart.model.Order;
import com.cart.util.ConnectionPool;
import com.cart.util.Utility;

public class OrderDAO implements IDao<Order> {
	
	private final String TABLE_NAME = "order_";
	
	@Override
	public void add(Order order) {
		
		String query = "INSERT INTO " + TABLE_NAME + " values(null, ?)";		
		
		try 
		{
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement ps = con.prepareCall(query);
			
			ps.setInt(1, order.getUser().getId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				order.setId(id);
			}			
			
			Utility.closeAll(con, ps, rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void update(Order t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> list(int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
