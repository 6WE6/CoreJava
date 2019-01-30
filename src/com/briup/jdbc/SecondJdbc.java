package com.briup.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SecondJdbc {
	private String driver = "com.mysql.jdbc.Driver";//驱动程序
	
	private String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8";//请求资源协议
	
	private String user = "root";//用户名
	
	private String password = "root";//密码
	
	/**
	 * SQL语句类型
	 * 程序员常用：查询语句(select)、DML语句(insert、update、delete)、DDL语句(create、alter、drop、truncate)
	 *         DCL语句(grant、revoke)、事务控制语句(commit、rollback、savepoint)
	 */
	
	public void createTable(){
		Connection conn = null;
		Statement sts = null;
		//step one:加载驱动
		try {
			Class.forName(driver);
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			//step three:创建Statement对象
			sts = conn.createStatement();
			//注意mysql中的数据类型和Java中数据类型的对应
			String sql = "create table s_teacher("
					+"id int primary key auto_increment,"
					+"name varchar(20) not null,"
					+"age int,"
					+"birth date"
					+")";
			//step four:执行sql语句
			//注意：执行sql语句常用的三种方法execute(String sql)、executeUpdate(String sql)、executeQuary(String sql)
			boolean bl = sts.execute(sql);
			System.out.println(bl);
			//step five:处理结果集或忽略
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//step six:关闭资源
				try {
					if(sts!=null)  sts.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}	
	}
	
	
	/**
	 * 补充解释：step three有两种方案：创建一个Statement对象，执行一条sql语句/创建一个Statement对象，执行多条sql语句。
	 * 但Statement对象是不能无限创建的
	 */
	public void insert(){
		Connection conn = null;
		Statement sts = null;
		try {
			//step one:加载驱动
			Class.forName(driver);
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			//step three:创建Statement对象
			sts = conn.createStatement();
			String sql = "insert into s_teacher values(1,'Tom',18,'2019-01-19')";
			//step four:执行sql语句
			int n = sts.executeUpdate(sql);
			System.out.print(n);
			//step five:处理返回结果或者省略
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//step six:关闭资源(注：java默认自动提交事务)
				try {
					if(sts!=null)   sts.close();
					if(conn!=null)  conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public void insert(List<Teacher> list){
		Connection conn = null;
		Statement sts = null;
		
		try {
			//step one:加载驱动
			Class.forName(driver);
			//说明连接对象不能无限创建
//			for(int i=0;i<1000;i++){
//				conn = DriverManager.getConnection(url, user, password);
//				System.out.println(i+"--"+conn);
//			}
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			//说明Statement对象也不能无限创建
//			for(int i=0;i<1000000;i++){
//				sts = conn.createStatement();
//				System.out.println(i);
//			}
			//step three:创建Statement对象
			sts = conn.createStatement();
			//step four:执行sql语句
			     //时间与字符串之间的转化
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			for(int i=0;i<list.size();i++){
				Teacher teacher = list.get(i);
				String str = sdf.format(teacher.getBirth());
				//思考该条sql语句:1.除了在创建表时使用序列设置为自动增长外，还有什么方法.2.时间类型
				String sql = "insert into s_teacher values(null,'"+teacher.getName()+"',"+teacher.getAge()+",'2019-01-20')";
				sts.execute(sql);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//step six:关闭资源(注：java默认自动提交事务)
			try {
				if(sts!=null)   sts.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}
	
	public List<Teacher> findAllTeacher(){
		Connection conn = null;
		Statement sts = null;
		ResultSet res = null;
		List<Teacher> list = new ArrayList<>();
		try {
			//step one:加载驱动
			Class.forName(driver);
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			//step three:创建Statement对象
			sts = conn.createStatement();
			String sql = "select * from s_teacher";
			//step four:执行sql语句
			res = sts.executeQuery(sql);
			//step five:处理结果集或省略
			while(res.next()){
				/**
				 * 取值有两种方式：方式一 ：基于在列中的位置取值，注意类型
				 */
//				Integer id = res.getInt(1);
//				String name = res.getString(2);
//				Integer age = res.getInt(3);
//				Date date = res.getDate(4);
//			    System.out.println(id+"-"+name+"-"+age+"-"+date);
			    //方式二：基于列的名字取值(如果有别名，可以用别名代替)
			    Integer id = res.getInt("id");
			    String name = res.getString("name");
			    Integer age = res.getInt("age");
			    Date date = res.getDate("birth");
			    System.out.println(id+"-"+name+"-"+age+"-"+date);
			    Teacher teacher = new Teacher(id,name,age,date);
			    list.add(teacher);
			}	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//step six:关闭资源(注：java默认自动提交事务)
			try {
				if(res!=null)   res.close();
				if(sts!=null)   sts.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return list;	
		
	}
	
	public Teacher findTeacherById(Integer id){
		Connection conn = null;
		Statement sts = null;
		ResultSet res = null;
		Teacher teacher = null;
		try {
			//step one:加载驱动
			Class.forName(driver);
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			//step three:创建Statement对象
			sts = conn.createStatement();
			String sql = "select * from s_teacher where id = "+id+"";
			//step four:执行sql语句
			res = sts.executeQuery(sql);
			//step five:处理结果集或省略
			while(res.next()){
				/**
				 * 取值有两种方式：方式一 ：基于在列中的位置取值，注意类型
				 */
//				Integer id = res.getInt(1);
//				String name = res.getString(2);
//				Integer age = res.getInt(3);
//				Date date = res.getDate(4);
//			    System.out.println(id+"-"+name+"-"+age+"-"+date);
			    //方式二：基于列的名字取值(如果有别名，可以用别名代替)
			    Integer newid = res.getInt("id");//注：这里的newid与方法形参的id不同
			    String name = res.getString("name");
			    Integer age = res.getInt("age");
			    Date date = res.getDate("birth");
			    System.out.println(newid+"-"+name+"-"+age+"-"+date);
			    teacher = new Teacher(newid,name,age,date);
			}	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//step six:关闭资源(注：java默认自动提交事务)
			try {
				if(res!=null)   res.close();
				if(sts!=null)   sts.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return teacher;
		
	}
	
	public void deleteTeacherById(Integer id){
		Connection conn = null;
		Statement sts = null;
		try {
			//step one:加载驱动
			Class.forName(driver);
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			//step three:创建Statement对象
			sts = conn.createStatement();
			String sql = "delete from s_teacher where id = "+id+"";
			//step four:执行sql语句
			int n = sts.executeUpdate(sql);
			System.out.println(n);
			//step five:处理结果集或省略	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//step six:关闭资源(注：java默认自动提交事务)
			try {

				if(sts!=null)   sts.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}
	
	public void updateTeacher(Teacher teacher){
		Connection conn = null;
		Statement sts = null;
		try {
			//step one:加载驱动
			Class.forName(driver);
			//step two:获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			//step three:创建Statement对象
			sts = conn.createStatement();
			String sql = "update s_teacher set name='"+teacher.getName()+"',age='"+teacher.getAge()+"',birth='"+teacher.getBirth()+"' where id = "+teacher.getId()+"";
			//step four:执行sql语句
			int n = sts.executeUpdate(sql);
			System.out.println(n);
			//step five:处理结果集或省略	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//step six:关闭资源(注：java默认自动提交事务)
			try {

				if(sts!=null)   sts.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	}

	public static void main(String[] args) {	
        //插入集合元素
//		List<Teacher> list = new ArrayList<>();
//		for(int i=0;i<10;i++){
//			//注：时间属性
//			list.add(new Teacher("Jack"+i,19+i,new Date(System.currentTimeMillis())));
//		}
//		new SecondJdbc().insert(list);
		
		//查询所有
//		List<Teacher> list = new SecondJdbc().findAllTeacher();
//		for(Teacher t : list){
//			System.out.println(t);
//		}
		
		//根据id查询某对象
//		System.out.println(new SecondJdbc().findTeacherById(7));

		//根据id删除对象
//		new SecondJdbc().deleteTeacherById(2);
		
		//修改对象
//		new SecondJdbc().updateTeacher(new Teacher(7, "Marry", 89, new Date(System.currentTimeMillis())));
		
	}

}
