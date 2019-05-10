package com.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.cart.model.OrderItem;
import com.cart.util.ConnectionPool;
import com.cart.util.Utility;

public class OrderItemDAO implements IDao<OrderItem> {

	private final String TABLE_NAME = "orderitem";
	
	@Override
	public void add(OrderItem orderItem) {
		
		String sql = "INSERT INTO " + TABLE_NAME + " values(null, ?, ?, ?)";
		
		try{
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement ps = con.prepareCall(sql); 
			
			ps.setInt(1, orderItem.getProduct().getId());
			ps.setInt(2, orderItem.getNum());
			ps.setInt(3, orderItem.getOrder().getId());
			
			ps.execute();			
			
			Utility.closeAll(con, ps);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(OrderItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderItem get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> list(int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
