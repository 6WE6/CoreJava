package com.briup.iocore;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Java的IO流是从程序运行所在内存的角度来划分的.
 * 1.InputStream/Reader、OutputStream/Writer四个抽象基类
 * 
 * @author WE
 *
 */
public class FirstIo {
	public static void main(String[] args) throws IOException {
		//创建字节输入流
		FileInputStream fis = new FileInputStream("src/com/briup/iocore/FirstIo.java");
		//创建一个长度为1024的数组
		byte[] bbuf = new byte[1024];
		//用于保存实际读取的字节数
		int hasRead = 0;
		//利用循环来重复从字节输入流中取出数据
		while((hasRead = fis.read(bbuf))>0){
			//将字节数组转换成字符串输出
			System.out.println(new String(bbuf, 0, hasRead));
		}
		//关闭文件输入流
		fis.close();
		
		System.out.println("------------------------------------------------------------------");
		
		//创建字符输入流
		FileReader fr = new FileReader("src/com/briup/iocore/FirstIo.java");
		//创建一个长度为32的字符数组
		char[] cbuf = new char[32];
		int factRead = 0;
		while((factRead = fr.read(cbuf))>0){
			System.out.println(new String(cbuf, 0, factRead));
		}
		fr.close();
		
		
	}
	

}
