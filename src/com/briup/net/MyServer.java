package com.briup.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 实际应用中的客户端则可能需要和服务器端保持长时间的通信，即服务器端需要不断的读取客户端数据，并向客户端写入数据，客户端也需要不断的读取服务器端的数据，并向服务器端写入数据；在使用传统的BufferedReader的readLine方法读取数据时，在该方法成功返回之前
 * 线程被阻塞，程序无法继续执行，考虑到这个原因，服务器端应该为每个Socket的单独启动一个线程，每个线程负责与一个客户端进行通信；客户端读取服务器端数据的线程同样会被阻塞，所以系统应该单独启动一个线程，该线程专门负责读取服务器端数据；
 * 1.考虑一个C/S聊天室应用，服务器端应该包含多个线程，每个Socket对应一个线程，该线程负责读取Socket对应输入流的数据（从客户端发送过来的数据），并将读取到的数据向每个Socket输出流发送一次，（将一个客户端发送的数据广播给其它客户端），因此需要在服务器端使用
 * List来保存所有的Socket
 * 
 * 
 * 该程序实现了服务器端只负责接收客户端Socket的连接请求，每当客户端Socket连接到该ServerSocket之后，程序将对应Socket加入socketList集合中保存，并为该socket启动一个线程，该线程负责处理该Socket所有的通信任务
 * @author WE
 *
 */
public class MyServer 
{
	//定义保存所有Socket的ArrayList,并将其包装为线程安全的
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
	public static void main(String[] args) throws Exception 
	{
		//创建一个ServerSocket,用于监听客户端的Socket连接请求
		ServerSocket ss = new ServerSocket(30000);
		while(true)
		{
			//此行代码会阻塞，将一直等待别人的连接;该accept方法将监听来自客户端的连接请求
			Socket s = ss.accept();
			socketList.add(s);
			//每当客户端连接后启动一个ServerThread线程为该客户端服务
			new Thread(new ServerThread(s)).start();
		}
		
	}
	

}
