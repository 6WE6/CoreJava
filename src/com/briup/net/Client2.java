package com.briup.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Client2 
{
	private static final int SERVER_PORT = 30001;
	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;

	public void init()
	{
		try {
		//初始化代表键盘的输入流
		keyIn = new BufferedReader(new InputStreamReader(System.in));
		//连接到服务器
			socket = new Socket("127.0.0.1",SERVER_PORT);
			//获取该socket对应的输出流和输入流
			ps = new PrintStream(socket.getOutputStream());
			brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip = "";
			//采用循环不断的弹出对话框要求输入用户名
			while(true)
			{
				String userName = JOptionPane.showInputDialog(tip+"输入用户名");
				//在用户输入的用户名前后增加协议字符串后发送
				ps.println(CustomProtocol.USER_ROUND+userName+CustomProtocol.USER_ROUND);
				//读取服务器端的相应
				String result = brServer.readLine();
				//如果用户名重复，则开始下次循环
				if(result.equals(CustomProtocol.NAME_REP))
				{
					tip = "用户名重复！请重新";
					continue;
				}
				//如果服务器端返回登陆成功，则结束循环
				if(result.equals(CustomProtocol.LOGIN_SUCCESS))
				{
					break;
				}
			}
		} 
		//捕获到异常，关闭网络资源，并退出该程序
		catch (IOException e) {
			System.out.println("网络异常，请重新登陆！");
			closeRs();
			System.exit(1);
		}
		//以该Socket对应得输入流启动ClientThread线程
		new ClientThreadPlus(brServer).start();
	}
	
	//定义一个读取键盘输入，并向网络发送的方法
	public void readAndSend(){
		//不断的读取键盘输入
		String line = null;
		try {
			while((line = keyIn.readLine()) != null)
			{
				//如果发送的信息中有冒号，且以//开头，则认为想发送私聊信息
				if(line.indexOf(":")>0 && line.startsWith("//"))
				{
					line = line.substring(2);
					ps.println(CustomProtocol.PRIVATE_ROUND + line.split(":")[0] +CustomProtocol.SPLIT_SIGN + line.split(":")[1] + CustomProtocol.PRIVATE_ROUND);
				}
				else{
					ps.println(CustomProtocol.MSG_ROUND + line +CustomProtocol.MSG_ROUND);
				}
			}
		} 
		//捕获到异常，关闭网络资源，并退出该程序
		catch (IOException e) {
			System.out.println("网络通信异常，请重新登陆！");
			closeRs();
			System.exit(1);
		}
	}
	//关闭Socket,输入流，输出流的方法
	private void closeRs() {
		if(keyIn != null)
		{
			ps.close();
		}
		if(brServer != null){
			ps.close();
		}
		if(ps != null){
			ps.close();
		}
		if(socket != null){
			try {
				keyIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		Client2 client2 = new Client2();
		client2.init();
		client2.readAndSend();
	}

}
