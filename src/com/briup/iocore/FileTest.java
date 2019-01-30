package com.briup.iocore;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * File类：File类是java.io包下代表与平台无关的文件和目录，在程序中操作文件和目录通过File类来完成.
 * 1.访问文件和目录：File类可以使用文件路径字符串来创建File实例，该文件路径字符串既可以是绝对路径也可以是相对路径.
 * 2.文件过滤器：在File类的list()方法中可以接收一个FilenameFilter参数，通过该参数可以只列出符合条件的文件
 * @author WE
 *
 */
public class FileTest {

	public static void main(String[] args) throws IOException {
		//以当前路径来创建一个File对象
		File file = new File(".");
		//直接获取文件名，输出一点
		System.out.println(file.getName());
		System.out.println(file.getPath());
		System.out.println(file.getParent());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getAbsoluteFile().getParent());
//		System.out.println(file.renameTo(new File("hi")));
		System.out.println(file.exists());
		
		//在当前路径下创建一个临时文件
		File tmpFile = File.createTempFile("aaa", ".txt", file);
		//指定当JVM退出时删除该文件
		tmpFile.deleteOnExit();
		//以系统当前时间作为文件名，来创建新文件
		File file2 = new File(System.currentTimeMillis()+"");
		System.out.println("file2对象是否存在："+file2.exists());
	//	System.out.println(file2.createNewFile());
		System.out.println(file2.mkdirs());
		//使用list方法列出当前路径下的所有文件和路径
		String[] fileList = file.list();
		System.out.println("当前路径下的所有文件和路径如下：");
		for(String fileName : fileList){
			System.out.println(fileName);
		}
		//listRoots方法列出磁盘的所有根路径
		File[] listRoots = File.listRoots();
		for(File root : listRoots){
			System.out.println(root);
		}
		//实现文件过滤功能,注意短路与和与操作运算符
		String[] nameList = file.list(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				return name.endsWith(".java,.txt")|!new File(name).isDirectory();
			}
		});
		for(String name:nameList){
			System.out.println(name);
		}

}
}
