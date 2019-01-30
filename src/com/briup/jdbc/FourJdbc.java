package com.briup.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务详述:java的JDBC默认自动提交事务，但在编程中希望我们自己控制事务的提交
 * 只有insert,delete,update语句需要手动提交事务
 * @author WE
 *
 */
public class FourJdbc {
	private String driver = "com.mysql.jdbc.Driver";//驱动程序
	
	private String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8";//请求资源协议
	
	private String user = "root";//用户名
	
	private String password = "root";//密码
	
	public void insertTeacher(Teacher teacher){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			//设置手动提交事务，默认为true,设置为false之后，若不手动提交事务，则无法成功保存到数据库中
			conn.setAutoCommit(false);
			String sql = "insert into s_teacher values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, teacher.getId());
			ps.setString(2, teacher.getName());
			ps.setInt(3, teacher.getAge());
			ps.setDate(4, teacher.getBirth());
			ps.execute();
			//手动提交事务
			conn.commit();
		}catch (Exception e) {
			//若在向数据库中保存数据时，在提交事务前，保存数据的中间出错了，导致数据没有保存到数据库中，则可事务回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
//			try {
//				if(ps!=null)  ps.close();
//				if(conn!=null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
	}
	}
	

	public static void main(String[] args) {
		new FourJdbc().insertTeacher(new Teacher(3, "lisi", 18, new Date(System.currentTimeMillis())));

	}

}
