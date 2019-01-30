package com.briup.iocore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * OutputStream、Writer
 * 1.Java的IO流执行输出时，不要忘记关闭输出流，关闭输出流除了可以保证流的物理资源被回收之外，可能还可以将输出流缓冲区中的数据flush到物理节点里(因为在执行close方法之前，自动执行输出流的flush()方法)
 * 2.如果希望直接输出字符串内容 则使用Writer会有更好的效果,注意要刷新或者关闭流才能将数据写入文件中
 * @author WE
 *
 */
public class SecondIo {

	public static void main(String[] args) {
		 try {
			//创建字节输入流
//			FileInputStream fis = new FileInputStream("src/com/briup/iocore/SecondIo.java");
//			//创建字节输出流
//			FileOutputStream fos = new FileOutputStream("src/com/briup/iocore/newFile.txt");
			//创建字符输出流
			FileWriter fw = new FileWriter("src/com/briup/iocore/poem.txt");
//			{
//				byte[] bbuf = new byte[32];
//			    int hasRead = 0;
//			    while((hasRead = fis.read(bbuf))>0){
//			    	//没读取一次，即写入文件输出流
//			    	fos.write(bbuf, 0, hasRead);
//			    }
//			}
			{
				fw.write("锦瑟 -李商隐\r\n");
				fw.write("锦瑟无端五十弦，一弦一柱思华年。\r\n");
				fw.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
