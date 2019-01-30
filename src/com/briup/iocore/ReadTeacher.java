package com.briup.iocore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadTeacher {

	public static void main(String[] args) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/briup/iocore/teacher.txt"));
			{
				//依次读取ObjectInputStream输入流中的四个对象
				Teacher t1 = (Teacher) ois.readObject();
				Teacher t2 = (Teacher) ois.readObject();
				ObjectSequenceBean osb = (ObjectSequenceBean) ois.readObject();
				Teacher t3 = (Teacher) ois.readObject();
				//
				System.out.println("t1的student引用和p是否相同:"+(t1.getStudent() == osb));
				System.out.println("t2的student引用和p是否相同:"+(t2.getStudent() == osb));
				System.out.println("t2和t3是否引用同一个对象:"+(t2 == t3));
				
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
