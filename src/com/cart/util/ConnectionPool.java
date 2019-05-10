package com.cart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {	
	
	private final String DATABASE_NAME = "cart";
	private final String USER = "root";
	private final String PASSWORD = "root";
	
	private List<Connection> clist = new ArrayList<>();
	private int size = 10;
	
	private static ConnectionPool INSTANCE = null;
	
//	public static Connection getConnection() throws SQLException, ClassNotFoundException {		
//		
//		Class.forName("com.mysql.cj.jdbc.Driver");		
//		return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?serverTimezone=UTC&useSSL=false", USER, PASSWORD);
//	}
//	
	private ConnectionPool() {		
		init();
	}
	
	public static ConnectionPool getInstance() {
		if (INSTANCE == null)
			INSTANCE = new ConnectionPool();
		
		return INSTANCE;
	}
	
	private void init() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");				
			
			for (int i = 0; i < size; i++) {
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?serverTimezone=UTC&useSSL=false", USER, PASSWORD);
				clist.add(c);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// 
			e.printStackTrace();
		}
	}
	
	public synchronized Connection getConnection() {
        while (clist.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Connection c = clist.remove(0);
        return c;
    }
  
    public synchronized void returnConnection(Connection c) {
        clist.add(c);
        this.notifyAll();
    }
	
	
	
	
	
}
