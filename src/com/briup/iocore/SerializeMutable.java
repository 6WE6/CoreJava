package com.briup.iocore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 流的操作规律：

在进行数据操作时，IO包中提供了N多对象不同功能来操作设备上的数据。
在实际开发时，到底用哪个流对象来完成数据处理呢？
这是我们最为苦恼的事情。
如何明确具体用哪个流对象呢？


规律就是四个明确？

1，明确源和目的。
	源：InputStream   Reader 一定是被读取的。
	目的：OutputStream  Writer 一定是被写入的。 
	
2，处理的数据是否是纯文本的数据？
	是：使用字符流。        Reader Writer
	否：使用字节流。	InputStream  OutputStream
	
	如果是源并且是纯文本，Reader
	如果是目的并且是纯文本，Writer
	
	到这里，两个明确确定完，就可以确定出要使用哪个体系。
	
	接下来，就应该明确具体这个体系要使用哪个具体的对象。

3，明确数据所在的设备：
	源设备：
		键盘(System.in)
		硬盘(FileXXX)FileReader FileInputStream
		内存(数组)ByteArrayInputStream CharArrayReader StringReader
		网络(Socket)
	目的设备：
		显示器(控制台System.out)
		硬盘(FileXXX)FileWriter FileOutputStream
		内存(数组)ByteArrayOutputStream CharArrayWriter StringWriter
		网络(Socket)
	
	具体使用哪个对象就可以明确了。
	
4，明确是否需要额外功能？
	1，是否需要高效？缓冲区Buffered 四个。 
	2，是否需要转换？转换流	InputStreamReader OutputStreamWriter	
	3，是否操作基本数据类型？ DataInputStream  DataOutputStream
	4，是否操作对象(对象序列化)？ ObjectInputStream ObjectOutputStream 
	5，需要对多个源合并吗？ SequenceInputStream
	6，需要保证数据的表现形式到目的地吗？ PrintWriter 
	
如果数据有规律，并且源和目的都是file，需要随机访问时，可以使用RandomAccessFile工具类。
 * @author WE
 *
 */
public class SerializeMutable {

	public static void main(String[] args) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mutable.txt"));
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mutable.txt"));
			{
				ObjectSequenceBean osb = new ObjectSequenceBean("孙爷爷", 500);
				//系统将osb对象转换成字节序列并输出
				oos.writeObject(osb);
				osb.setName("猪悟能");
				oos.writeObject(osb);
				//系统只是输出序列化编号,所以改变后的name不会被序列化
				ObjectSequenceBean osb1 = (ObjectSequenceBean) ois.readObject();
				ObjectSequenceBean osb2 = (ObjectSequenceBean) ois.readObject();
				//下面输出true,即反序列化后p1等于p2
				System.out.println(osb1 == osb2);
				System.out.println(osb2.getName());
				
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
