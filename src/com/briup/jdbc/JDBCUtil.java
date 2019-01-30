package com.briup.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtil {
	public static List<Connection> pool;
	
	static{
		Properties pro = new Properties();
		try {
			pool = new ArrayList<>();
			pro.load(new FileInputStream("src/com/briup/jdbc/jdbc.properties"));
			Class.forName(pro.getProperty("driver"));
			for(int i=0;i<10;i++){
				Connection conn = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("user"), pro.getProperty("password"));
				pool.add(conn);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws SQLException{
		if(pool.size()>0){
			Connection conn = pool.get(0);
			pool.remove(0);
			return conn;	
		}else{
			throw new SQLException("没有连接对象了！");
		}
	}
	
	public static void releaseConn(Connection conn){
		pool.add(conn);
	}
	
	public static void close(Connection conn,PreparedStatement ps){
			try {
				if(ps!=null)
				ps.close();
				pool.add(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		
	}
	
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			pool.add(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
}
	public static void close(Connection conn,Statement ps){
		try {
			if(ps!=null)
			ps.close();
			pool.add(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	
}

  public static void close(Connection conn,Statement ps,ResultSet rs){
	try {
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		pool.add(conn);
	} catch (SQLException e) {
		e.printStackTrace();
	}	

}

}
