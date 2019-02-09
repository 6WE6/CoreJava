package com.briup.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 每个客户端应该包含两个线程，一个负责读取用户的键盘输入，并将用户输入的数据写入Socket对应的输出流中，一个负责读取Socket对应输入流中的数据(从服务器端发送过来的数据),并将这些数据打印输出，其中负责读取用户键盘输入的线程由MyClient负责
 * ，也就是由程序的主线程负责
 * @author WE
 *
 */
public class ClientThread implements Runnable 
{
	//该线程负责处理的Socket
	private Socket s;
	//该线程所处理的Socket对应的输入流
	BufferedReader br = null;
	public ClientThread(Socket s) throws IOException 
	{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() 
	{
		String content = null;
		//不断读取Socket输入流中的内容，并将这些数据打印输出
		try {
			while((content = br.readLine()) != null)
			{
				System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
