package com.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cart.model.User;
import com.cart.util.ConnectionPool;
import com.cart.util.Utility;

public class UserDAO implements IDao<User> {

	private final String TABLE_NAME = "user";

	@Override
	public void add(User t) {

	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User get(int id) {

		User user = null;
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID=" + id;

		try {
			
			Connection con = ConnectionPool.getInstance().getConnection(); 
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
			
			Utility.closeAll(con, st);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public User get(String name, String password) {

		User user = null;
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE name=? and password=?";

		try {
			
			Connection con = ConnectionPool.getInstance().getConnection(); 
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
			
			Utility.closeAll(con, ps, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list(int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
