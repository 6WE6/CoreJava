package com.briup.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 注：一旦使用ServerSocket,Socket建立网络连接之后，程序通过网络通信与普通IO并没有太大的区别
 * 1.在实际应用中，程序可能不想让执行网络连接，读取服务器数据的进程一直阻塞，而是希望当网络连接，读取操作超过合理时间之后，系统自动认为该操作失败
 * 		Socket socket = new Socket("127.0.0.1", 30000);
 *      //设置10s之后即认为超时
 *      socket.setSoTimeout(10000);
   2.假设程序需要为Socket连接服务器时指定超时时长，即经过指定时间后，如果该Socket还未连接到远程服务器，则系统认为该Socket连接超时，做法是：程序应该先建立一个无连接的Socket,在调用Socket的connect方法来连接远程服务器
        //创建一个无连接的Socket
        Socket socket = new Socket();
        //让该Socket连接到远程服务器，如果经过10s还没哟连接上，则认为连接超时
        socket.connect(new InetSocketAddress(host,port),10000);
 * @author WE
 *
 */
public class Client 
{
	public static void main(String[] args) throws IOException 
	{
		//使用Socket建立与指定IP地址，指定端口的连接
		Socket socket = new Socket("127.0.0.1", 30000);
		//将Socket对应的输入流包装成BufferedReader
		BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    //进行普通的IO操作
		String line = br.readLine();
		System.out.println("来自服务器的数据："+line);
		//关闭输入流，关闭Socket
		br.close();
		socket.close();
	}

}
