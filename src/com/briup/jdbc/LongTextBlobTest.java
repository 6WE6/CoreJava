package com.briup.jdbc;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试如何存储超长文本信息(文章）和较大的二进制对象(图片，音乐，视频等)
 * @author WE
 * String sql = "create table computer(id int primary key auto_increment,data longtext)";对应Oracle中的clob数据类型
 * String sql = "create table mouse(id int primary key auto_increment,data longblob)";对应Oracle中的Blob数据类型
 */


public class LongTextBlobTest {
	private String driver = "com.mysql.jdbc.Driver";//驱动程序
	
	private String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8";//请求资源协议
	
	private String user = "root";//用户名
	
	private String password = "root";//密码
	
	public void insertLongText(){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into computer values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			//1.如果内容全是数字或字母则可调用Ascii方法,第二个参数是字节流类型
//			File file = new File("src/com/briup/jdbc/mysql.txt");
//			ps.setAsciiStream(2, new FileInputStream(file));
//			ps.setAsciiStream(2, new FileInputStream(file),file.length());
			//2.如果内容有字符,注意编码问题
			File file = new File("src/com/briup/jdbc/jdbc.txt");
			ps.setCharacterStream(2,new FileReader(file));
			ps.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getLongText(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from computer";
			ps = conn.prepareStatement(sql);
			res = ps.executeQuery();
			while(res.next()){
				
				Integer id = res.getInt("id");
				//方式一  常用
				Clob data = res.getClob("data");
				Reader reader = data.getCharacterStream();
			    //方式二  不建议用，有可能取不到值
//				Reader reader = res.getCharacterStream("data");
				//将读到的字符流打印出来
				BufferedReader br = new BufferedReader(reader);
				String str = null;
				while((str = br.readLine())!=null){
					System.out.println(str);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertLongBlob(){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into mouse values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			File file = new File("src/com/briup/jdbc/jdbc.txt");
			ps.setBlob(2, new FileInputStream(file));
			ps.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void getLongBlob(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from mouse";
			ps = conn.prepareStatement(sql);
			res = ps.executeQuery();
			while(res.next()){
				
				Integer id = res.getInt("id");
				//方式一  常用
				Blob data = res.getBlob("data");
				InputStream inputStream = data.getBinaryStream();
			    //方式二  不建议用，有可能取不到值
//				InputStream inputStream = res.getBinaryStream(2);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));//将字节流转化为字符流
				//将读到的字符流打印出来
				String str = null;
				while((str = br.readLine())!=null){
					System.out.println(str);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		
	}
	

	public static void main(String[] args) {
//		new LongTextBlobTest().insertLongText();
//		new LongTextBlobTest().getLongText();
//		new LongTextBlobTest().insertLongBlob();
		new LongTextBlobTest().getLongBlob();


	}

}
