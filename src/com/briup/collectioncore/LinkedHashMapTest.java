package com.briup.collectioncore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Properties;

/**
 * LinkedHashMap、LinkedHashSet与Properties:
 * 1.LinkedHashMap是HashMap的子类，使用双向链表来维护key-value对的次序，迭代顺序与key-value对的插入顺序保持一致
 * 2.Properties类是Hashtable类的子类，处理属性文件特别方便，Properties类可以把Map对象和属性文件关联起来，从而可以把Map对象中的key-value对写入属性文件中，也可以把属性文件中的内容加载到Map对象中，属性文件中的属性名和属性值只能是
 * 字符串类型，Properties相当于一个key,value都是String类型的Map
 * 3.LinkedHashSet是HashSet的子类，根据元素的的hashCode值来决定元素的存储位置，同时使用链表维护元素的次序
 * @author WE
 *
 */
public class LinkedHashMapTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("--------------LinkedHashMap---------------");
		LinkedHashMap scores = new LinkedHashMap();
		scores.put("语文", 80);
		scores.put("英语", 85);
		//使用forEach方法来遍历Map集合
		scores.forEach((key,value)->System.out.println(key + "-->" +value));
		System.out.println("-------------使用Properties读写属性文件--------------");
		Properties props = new Properties();
		//向properties里添加属性
		props.setProperty("username", "kuku");
		props.setProperty("password", "kuku");
		//将Properties中的key-value对保存到a.ini文件中
		props.store(new FileOutputStream("src/com/briup/collectioncore/a.ini"), "comment line");
		Properties props2 = new Properties();
		props2.setProperty("gender", "male");
		//将a.ini文件中的key-value对追加到props中
		props.load(new FileInputStream("src/com/briup/collectioncore/a.ini"));
		System.out.println(props2);
		System.out.println("---------------------LinkedHashSet--------------");
		LinkedHashSet lh = new LinkedHashSet();
		lh.add("qewer");
		lh.add("defds");
		lh.add("dsdfsdjfdfnd");
		System.out.println(lh);

		
		

	}

}
