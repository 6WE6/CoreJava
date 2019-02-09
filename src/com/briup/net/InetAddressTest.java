package com.briup.net;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 1.Java提供InetAddress类来代表IP地址
 * 2.URLDecoder和URLEncoder用于完成普通字符串和application/x-www-form-urlencoder MIME字符串之间的相互转换
 * @author WE
 *
 */
public class InetAddressTest 
{
	public static void main(String[] args) throws Exception 
	{
		//根据主机名来获取对应的InetAddress实例
		InetAddress ip = InetAddress.getByName("www.crazyit.org");
		//判断是否可达
		System.out.println("crazy是否可达："+ip.isReachable(2000));
		//获取该InetAddress实例的ip字符串
		System.out.println(ip.getHostAddress());
		//根据原始ip地址来获取对应的InetAddress实例
		InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		System.out.println("本机是否可达："+local.isReachable(5000));
		//获取该InetAddress实例对应的全限定域名
		System.out.println(local.getCanonicalHostName());
		
		System.out.println("----------------------------------");
		//将专业字符串转换成普通字符串
		String keyWord = URLDecoder.decode("%E7%96%AF%82java", "utf-8");
		System.out.println(keyWord);
		//将普通字符串转换成专业字符串
		String urlStr = URLEncoder.encode("疯狂Java讲义","GBK");
		System.out.println(urlStr);
		
	}

}
