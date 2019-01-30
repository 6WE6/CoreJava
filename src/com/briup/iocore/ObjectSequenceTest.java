package com.briup.iocore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSequenceTest {

	public static void main(String[] args) {
		//创建一个ObjectOutputStream输出流
//		try {
//			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/com/briup/iocore/object.txt"));
//			{
//				ObjectSequenceBean os = new ObjectSequenceBean("孙悟空",500);
//				//将os对像写入输出流
//				oos.writeObject(os);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
		
		
		/**
		 * 反序列化
		 * 反序列化读取的仅仅是Java对象的数据,而不是Java类,因此采用反序列化恢复Java对象时,必须提供该Java对象所属类的class文件,否则将会引起ClassNotFindException
		 * 如果使用序列化机制向文件中写入了多个Java对象,使用反序列化机制恢复对象时必须按实际写入的顺序读取
		 * 当一个序列化的类有多个父类时,这些父类要么有无参数的构造器,要么也时可序列化的----否则反序列化时将抛出InvalidClassException异常,如果父类是不可序列化的,只是带有无参数的构造器,则该父类中定义的成员变量值不会序列化到二进制流中
		 */
		//创建一个ObjectInputStream输入流
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/briup/iocore/object.txt"));
			{
				//从输入流中读取一个Java对象,并将其强转成ObjectSequenceBean类
				ObjectSequenceBean osb = (ObjectSequenceBean) ois.readObject(); 
				System.out.println("名字为:"+osb.getName()+"年龄为:"+osb.getAge());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
