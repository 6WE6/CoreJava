package com.briup.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FirstJdbc {
	/**
	 * 数据库连接四要素
	 */
	private String driver = "com.mysql.jdbc.Driver";//驱动程序
	
	private String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8";//请求资源协议
	
	private String user = "root";//用户名
	
	private String password = "root";//密码
	
	/**
	 * 注册驱动的四种方式
	 */
	//方式一 常用
	public void RegisterDriverOne(){
	    Connection conn = null;
		try {
			//step one:加载驱动
			Class.forName(driver);
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//方式二  手动注册驱动
	public void RegisterDriverTwo(){
		Connection conn = null;
		try {
			new com.mysql.jdbc.Driver();
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//方式三  注册驱动给虚拟机传参数
	public void RegisterDriverThree(){
		Connection conn = null;
		System.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//方式四   运行时给虚拟机传参数  -Djdbc.drivers=com.mysql.jdbc.Driver
	public void RegisterDriverFour(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接对象的两种方式
	 * 方式一: Connection conn = DriverManager.getConnection(url, user, password);
	 * 方式二：  利用Properties类来加载属性文件
	 */
	public void CreateConnection(){
		Connection conn = null;
		try {
			Class.forName(driver);
			//使用Properties类来加载属性文件,在用的时候不会这样去用，会单独写一个配置文件去加载
			//用法一
			Properties pro = new Properties();
		//	pro.setProperty("user", "root");
		//	pro.setProperty("password", "root");
			pro.load(new FileInputStream("src/com/briup/jdbc/mysql.txt"));
			conn = DriverManager.getConnection(url, pro);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	} 
	
	public static void main(String[] args) {
		new FirstJdbc().CreateConnection();
		
	}
	
	

}
