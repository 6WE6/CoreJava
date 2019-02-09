package com.briup.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的网络编程：TCP/IP通信协议是一种可靠的通信协议，它在通信的两端各建立一个Socket,从而在通信的两端之间形成网络虚拟链路，一旦建立了虚拟的网络链路，两端的程序就可以通过虚拟链路进行通信。java对基于TCP协议的网络通信提供了良好的封装，java
 * 使用Socket对象来代表两端的通信端口，并通过Socket产生IO流来进行网络通信
 * 1.IP协议时Internet上使用的一个关键协议，通过使用IP协议，从而使Internet成为一个允许连接不同类型的计算机和不同操作系统的网络；IP协议只保证计算机能发送和接收分组数据，IP协议负责将消息从一个主机传送到另一个主机，消息在传送的过程中被分割成一个个的小
 * 包；IP协议不能解决数据分组在传输过程中可能出现的问题
 * 2.TCP协议被称作一种端对端协议，它对两台计算机之间的连接起了重要作用，当一台计算机需要与另一台远程计算机连接时，TCP协议会让它们建立一个连接，用于发送和接收数据的虚拟链路 ；TCP协议负责收集这些信息包，并将其按适当的次序放好传送，接收端收到再将其正确
 * 的还原，TCP协议保证数据包在传送过程中准确无误；TCP协议使用重发机制----当一个通信实体发送一个消息给另一个通信实体后，需要收到另一个通信实体的确认信息，如果没有收到另一个通信实体的确认信息，则会再次重发刚才的确认信息；通过这种重发机制，TCP协议向
 * 应用程序提供了可靠的通信连接，使它能够自动适应网上的各种变化，即使在Internet暂时出现堵塞的情况下，TCP也能够保证通信的的可靠性
 * 3.Java中能接收其它通信实体连接请求的类是ServerSocket，ServiceSocket对象用于监听来自客户端的Socket连接，如果没有连接，它将一直处于等待状态；当ServiceSocket使用完毕后，应使用ServiceSocket的close方法来关闭该ServiceSocket
 * 在通常情况下，服务器不应该只接收一个客户端请求，而应该不断接收来自客户端的所有请求，所以Java程序通常会通过循环不断的调用ServerSocket的accept方法
 * 4.使用Socket进行通信：客户端通常可以使用Socket的构造器来连接到指定的服务器
 * @author WE
 *
 */
public class Service 
{
	public static void main(String[] args) throws IOException 
	{
		//创建一个ServerSocket，用于监听客户端的Socket连接请求
		ServerSocket ss = new ServerSocket(30000);
		//采用循环不断的接收来自客户端的请求
		while(true)
		{
			//每当接收到客户端的Socket的请求时，服务器端也对应产生一个Socket
			Socket s = ss.accept();
			//将Socket对应的输出流包装成PrintStream
			PrintStream ps = new PrintStream(s.getOutputStream());
			//进行普通IO操作
			ps.println("您好，您收到了服务器端的新年祝福");
			//关闭输出流，关闭Socket
			ps.close();
			s.close();
		}
		
	}

}
