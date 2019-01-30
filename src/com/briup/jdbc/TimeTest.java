package com.briup.jdbc;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.plaf.synth.SynthSeparatorUI;
/**
 * 时间转换
 * @author WE
 *
 */
public class TimeTest {

	public static void main(String[] args) {
		//1.java.util.Date提供了无参的构造器
//		java.util.Date date = new java.util.Date();
//		System.out.println(date);
		//2.过时的方法，要想正常显示时间，则年份-1900，月份-1
//		Date date = new Date(2019-1900, 1-1, 19);
//		System.out.println(date);
		//3.时间转换都要通过时间戳.时间戳是从1970年1月1日0分0秒开始的
//		long time = System.currentTimeMillis();
//		Date date = new Date(time);
//		System.out.println(date);
		//4.将java.util.Date转换成java.sql.Date.注：d.getTime()方法获取当前时间戳
//		java.util.Date d = new java.util.Date();
//		Date date = new Date(d.getTime());
//		System.out.println(date);
		//5.将java.sql.Date转换成java.util.Date.5的正确运行要靠4
//	    java.util.Date dat = new java.util.Date(date.getTime());
//	    System.out.println(dat);
		//6.将java.util.Date转换成Timestamp也要借助时间戳,Timestamp精确到了毫秒
//		java.util.Date d = new java.util.Date();
//		Timestamp tt = new Timestamp(d.getTime());
//		System.out.println(tt);
		//7.时间和字符串之间的转换.查看API.将java.util.Date转化为字符串
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
//		String date = sdf.format(new java.util.Date());
//		System.out.println(date);
		//8.将字符串转化为时间
		String str = "20-01-2019";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date date = sdf.parse(str);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		

	}

}
