package com.briup.iocore;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PushbackReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

/**
 * 处理流：使用处理流的思路是使用处理流来包装节点流，程序通过处理流来执行输入输出功能，让节点流来与底层的IO设备或文件交互
 * 识别处理流：只要流的构造器参数不是一个物理节点，而是已经存在的流，那么这种流就一定是处理流，而所有的节点流都是直接以物理IO节点作为构造器参数的
 * 1.使用处理流是只需要传入一个节点流作为构造器参数即可，在使用处理流包装了底层节点流之后，关闭输入输出流资源时，只要关闭最上层的处理流即可，关闭最上层的处理流时，系统会自动关闭被该处理流包装的节点流。
 * 2.Java将IO流按功能分成了许多类,而每类中又分别提供了字节流和字符流
 * a:抽象基类InputStream   OutputStream     Reader     Writer
 * b:访问文件：FileInputStream  FileOutputStream   FileReader    FileWriter
 * c:访问数组：ByteArrayInputStream    ByteArrayOutputStream    CharArrayReader    CharArrayWriter
 *   以数组为物理节点的节点流，除了在创建节点流对象时需要传入一个字节数组或字符数组之外，用法上与文件节点流完全相似，此外，字符流还可以使用字符串作为物理节点，用于实现从字符串读取内容
 * d:访问管道：PipedInputStream
 *   4个访问通道的流用于用于实现进程之间的通信功能
 * e:访问字符串：                                                                                                                                               StringReader       StringWriter
 *   与FileReader和FileWriter用法一样
 * f:缓冲流：BufferedInputStream
 *   缓冲流增加了缓冲功能，提高了输入输出的效率，增加缓冲功能后需要使用flush才可以将缓冲区的内容写入实际的物理节点
 * g:转换流：                                                                                                                                                      InputStreamReader   OutputStreamWriter
 *   转换流用于实现将字节流转换成字符流，InputStreamReader用于将字节输入流转换成字符输入流，OutputStreamWriter用于将字节输出流转换成字符输出流
 * h:对象流：ObjectInputStream  ObjectOutputStream
 *   2个对象流用于实现对象的序列化
 * i:抽象基类：FilterInputStream
 * j：打印流：                                                        PrintStream                      PrintWriter
 * k:推回输入流：PushBackInputStream                        PushBackReader
 *   推回输入流和InputStream、Reader里的read方法一一对应，这两个推回输入流都带有一个推回缓冲区，推回输入流每次调用read方法时总是先从推回缓冲区读取，只有完全读取了推回
 *   缓冲区的内容后，但还没有装满read方法所需的数组时才会从原输入流中读取
 * l:特殊流：DataInputStream   DataOutputStream
 * 3.通常来说，字节流的功能比字符流的功能强大，因为计算机里所有的数据都是二进制的，而字节流可以处理所有的二进制文件;问题是，如果使用字节流来处理文本文件，则需要使用合适的方式把这些字节转换成字符，太复杂，因此处理文本内容，则应该考虑使用字符流，如果是
 * 二进制内容，则应该考虑使用字节流，计算机的文件常被分为文本文件和二进制文件两大类，实质上文本文件是二进制文件的一种特例
 * 4.
 * 
 * @author WE
 *
 */
public class PrintStreamTest {

	public static void main(String[] args) {
		
		
//		try {
//			//先定义了一个节点输出流
//			FileOutputStream fos = new FileOutputStream("src/com/briup/iocore/test.txt");
//			//处理流对节点流进行封装，使用处理流进行输入输出操纵
//			PrintStream ps = new PrintStream(fos);
//			{
//				//使用printStream执行输出
//				ps.println("普通字符串");
//				ps.println(new PrintStreamTest());
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		
		/**
		 * 使用字符串作为物理节点的字符输入输出流的用法
		 */
//		String str = "每天都要做一个幸福的人\n"+"我想有一所房子，面朝大海，春暖花开\n";
//		char[] buffer = new char[32];
//		int hasRead = 0;
//		StringReader sr = new StringReader(str);
//		{
//			//采用循环读取的方式读取字符串
//			try {
//				while((hasRead = sr.read(buffer))>0){
//					System.out.println(new String(buffer, 0, hasRead));
//				}
//				System.err.println(sr.toString());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		//使用默认初始字符串缓冲区大小创建一个新字符串 writer。
//		StringWriter sw = new StringWriter();
//		{
//			//调用StringWriter的方法执行输出
//			sw.write("我的愿望是世界和平！");
//			System.out.println(sw.toString());
//		}
		
		/**
		 * 转换流测试
		 * Java使用System.in代表标准输入，但这个标准输入流是InputStream类的实例，使用不太方便，而且键盘输入的内容都是文本内容，所以可以使用InputStreamReader将其转换成字符输入流
		 * 普通得Reader读取输入内容不太方便，可以将Reader包装成BufferedReader,利用BufferedReader得readLine方法可以一次读取一行内容
		 */
//		//将System.in对象转换成Reader对象
//		InputStreamReader reader = new InputStreamReader(System.in);
//		//将普通的Reader包装成BufferedReader,从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
//		BufferedReader br = new BufferedReader(reader);
//		String line = null;
//		//采用循环方式来逐行的读取
//		try {
//			while((line = br.readLine())!=null){
//				if(line.equals("exit")){
//					System.exit(1);
//				}
//				//打印读取的内容
//				System.out.println("输入内容为："+line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		/**
		 * 推回输入流
		 */
		//创建一个PushbackReader对象，指定推回缓冲区的长度为64
//		try {
//			PushbackReader pr = new PushbackReader(new FileReader(" src/com/briup/iocore/PrintStreamTest.java"),64);
//			{
//				char[] buf = new char[32];
//				String lastContent = "";//用以保存上次读取的字符串内容
//				int hasRead = 0;
//				//循环读取文件内容
//				while((hasRead = pr.read(buf))>0){
//					//将读取的内容转换成字符串
//					String content = new String(buf,0,hasRead);
//					int targetIndex = 0;
//					//将上次读取的字符串和本次读取的字符串拼接起来
//					//查看是否包含目标字符串，如果包含目标字符串
//					if((targetIndex = (lastContent+content).indexOf("new PushbackReader"))>0){
//						//将本次内容和上次内容一起推回缓冲区
//						pr.unread((lastContent+content).toCharArray());
//						//重新定义一个长度为targetIndex的char数组
//						if(targetIndex > 32){
//							buf = new char[targetIndex];
//						}
//						//再次读取指定长度的内容(就是目标字符串之前的内容)
//						pr.read(buf, 0, targetIndex);
//						//打印读取的内容
//						System.out.print(new String(buf,0,targetIndex));
//						System.exit(0);
//					}else{
//						//打印上次读取的内容
//						System.out.println(lastContent);
//						//将本次内容设为上次读取的内容
//						lastContent = content;
//					}
//				}
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/**
		 * 重定向标准输入输出 
		 */
//		//创建PrintStream输出流
//		try {
//			PrintStream ps = new PrintStream(new FileOutputStream("src/com/briup/iocore/out.txt"));
//			//将标准输入输出重定向到ps输出流
//			{
//				System.setOut(ps);
//				System.out.println("hello");
//				System.out.println(new PrintStreamTest());
//				
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		/**
		 * 重定向到标准输入
		 * 创建一个FileInputStream输入流，并使用System的setIn()方法将系统标准输入重定向到该文件输入流，运行该程序，程序不会等待用户输入，而是直接输出了PrintStream的内容
		 */
		try {
			FileInputStream fs = new FileInputStream("src/com/briup/iocore/PrintStreamTest.java");
			{
				//将标准输入重定向到fs输入流
				System.setIn(fs);
				//使用System.in创建Scanner对象，用于获取标准输入
				Scanner sc = new Scanner(System.in);
				//增加下面一行，只把回车作为分隔符
				sc.useDelimiter("\n");
				//判断是否还有下一个输入项
				while(sc.hasNext()){
					System.out.println(sc.next());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
