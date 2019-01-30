package com.briup.iocore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 对象引用的序列化
 * @author WE
 *1. 如果某个类的成员变量的类型不是基本类型或String类型,而是另一个引用类型,那么这个引用类型必须是可序列化的,否则拥有该类型成员变量的类也是不可序列化的
 * e.g:如下Teacher类持有一个ObjectSequenceBean类的引用,只有ObjectSequenceBean类是可序列化的,Teacher类才是可序列化的,如果ObjectSequenceBean类不可序列化,则
 * 无论Teacher类是否实现Serilizable,Externalizable接口,则Teacher类都是不可序列化的.
 *2.Java的序列化机制采用了一种特殊的序列化算法:a:所有保存到磁盘中的对象都有一个序列化编号;b:当程序试图序列化一个对象时,程序将先检查该对象是否已经被序列化过,只有当该对象从未被序列化过
 *程序才会将该对象转换成字节序列并输出;c:如果某个对象已经被序列化过,程序将直接输出一个序列化编号.
 *3.当序列化一个可变对象时,只有第一次使用writeObject方法输出时,才会将该对象转换成字节序列并输出,当程序再次调用writeObject方法时,程序只是输出前面的序列化编号,即使后面该对象的实例变量值已被改变,
 *改变的实例变量值也不会被输出
 *
 */
public class WriterTeacher {

	public static void main(String[] args) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/com/briup/iocore/teacher.txt"));
			{
				ObjectSequenceBean osb = new ObjectSequenceBean("孙悟空",500);
				Teacher t1 = new Teacher("唐僧",osb);
				Teacher t2 = new Teacher("菩提老祖",osb);
				//依次将四个对象写入输出流
				oos.writeObject(t1);
				oos.writeObject(t2);
				oos.writeObject(osb);
				oos.writeObject(t2);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
