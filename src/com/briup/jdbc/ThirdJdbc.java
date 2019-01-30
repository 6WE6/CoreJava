package com.briup.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明Statement对象和PrepareStatement对象的区别
 * PrepareStatement执行sql语句只编译一次，编译好的sql语句，存储在数据库端，里面的参数采用占位，后期处理时，只需传入一组数据(id,name,age,birth)
 * 采用占位的数据设置在编译之后，执行之前，设置是基于问号处在第几个(同时还要关注类型)
 * Statement执行的时候，每条sql语句都要编译，编译好的sql保存在客户端(java程序端即JVM中)
 * @author WE
 *
 */

public class ThirdJdbc {
	private String driver = "com.mysql.jdbc.Driver";//驱动程序
	
	private String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8";//请求资源协议
	
	private String user = "root";//用户名
	
	private String password = "root";//密码
	
	//sql语句采用字符串拼接处理
	public void insertTeacher(Teacher teacher){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into s_teacher values('"+teacher.getId()+"','"+teacher.getName()+"','"+teacher.getAge()+"','"+teacher.getBirth()+"')";
			ps = conn.prepareStatement(sql);
			
			
			ps.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)  ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}
	
	//sql语句采用占位处理
	public void insertTeacherTwo(Teacher teacher){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into s_teacher values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, teacher.getId());
			ps.setString(2, teacher.getName());
			ps.setInt(3, teacher.getAge());
			ps.setDate(4, teacher.getBirth());
			
			ps.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)  ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}
	
	//插入集合
	public void insertTeacherThree(List<Teacher> teacher){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into s_teacher values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			//当前时间
			long start = System.currentTimeMillis();
			for(int i=0;i<teacher.size();i++){
			Teacher tea = teacher.get(i);
			ps.setInt(1, tea.getId());
			ps.setString(2, tea.getName());
			ps.setInt(3, tea.getAge());
			ps.setDate(4, tea.getBirth());
			ps.execute();
			}
			
			//当前时间
			long end = System.currentTimeMillis();
			System.out.println("sum:"+(end-start));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)  ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	}
	
	//加入批处理
	public void insertTeacherFour(List<Teacher> teacher){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into s_teacher values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			//当前时间
			long start = System.currentTimeMillis();
			for(int i=0;i<teacher.size();i++){
			Teacher tea = teacher.get(i);
			ps.setInt(1, tea.getId());
			ps.setString(2, tea.getName());
			ps.setInt(3, tea.getAge());
			ps.setDate(4, tea.getBirth());
			//构建一组数据放入缓存中
			ps.addBatch();
			if(i!=0&&i%3500==0){
				//同时传输1000条数据给数据库
				ps.executeBatch();
			}
			}
			//把余下的没传输进去的数据传输进去
			ps.executeBatch();
			
			//当前时间
			long end = System.currentTimeMillis();
			System.out.println("sum:"+(end-start));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)  ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	}
	
	
	
	public static void main(String[] args) {
//		new ThirdJdbc().insertTeacherTwo(new Teacher(3, "KK", 11, new Date(System.currentTimeMillis())));
		
		List<Teacher> list = new ArrayList<>();
		for(int i=0;i<10000;i++){
			//注：时间属性
			list.add(new Teacher(i,"Jack"+i,i,new Date(System.currentTimeMillis())));
		}
		new ThirdJdbc().insertTeacherThree(list);

	}

}
