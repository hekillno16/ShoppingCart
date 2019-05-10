package com.cart.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utility {
	
	public static void closeAll(Connection con) {
		ConnectionPool.getInstance().returnConnection(con);
	}
	
	public static void closeAll(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
		ConnectionPool.getInstance().returnConnection(con);
		ps.close();
		rs.close();
	}
	
	public static void closeAll(Connection con, PreparedStatement ps) throws SQLException {
		ConnectionPool.getInstance().returnConnection(con);
		ps.close();		
	}
	
	public static void closeAll(Connection con, Statement st) throws SQLException {
		ConnectionPool.getInstance().returnConnection(con);
		st.close();		
	}

}
