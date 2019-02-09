package com.briup.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
/**
 *  每个客户端应该包含两个线程，一个负责读取用户的键盘输入，并将用户输入的数据写入Socket对应的输出流中，一个负责读取Socket对应输入流中的数据(从服务器端发送过来的数据),并将这些数据打印输出，其中负责读取用户键盘输入的线程由MyClient负责
 * ，也就是由程序的主线程负责
 * 
 * MyServer、MyClient、ServerThread、ClientThread
 * 先运行上面程序中的MyServer类，该类运行只是作为服务器，看不到任何输出；
 * 在运行多个MyClient----相当于启动多个聊天室客户端登陆该服务器。然后可以在任何一个客户端通过键盘输入一些内容后按回车健，即可在所有客户端（包括自己）的控制台上收到刚刚输入的内容，这就粗略的实现了一个C/S结构聊天室的功能
 * @author WE
 *
 */
public class MyClient 
{
	public static void main(String[] args) throws Exception 
	{
		Socket s = new Socket("127.0.0.1",30000);
		//客户端启动ClientThread线程不断的读取来自服务器的数据
		new Thread(new ClientThread(s)).start();
		//获取该Socket对应的输出流
		PrintStream ps = new PrintStream(s.getOutputStream());
		String line = null;
		//不断的读取键盘输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while((line = br.readLine()) != null)
		{
			 //将用户的键盘输入内容写入Socket对应的输出流
			ps.println(line);
		}
		
	}

}
