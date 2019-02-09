package com.briup.reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 获得Class对象的三种方式
 * 1.使用Class类的forName静态方法，该方法需要传入字符串参数
 * 2.调用某个类的class属性来获取该类对应的Class对象
 * 3.调用某个对象的getClass方法
 * Class类提供了大量的实例方法来获取该Class对象所对应类的详细信息，通过Class对象可以得到大量的Method,Constructor，Field等对象，这些对象分别代表该类所包含的方法，构造器和成员变量等，程序还可以通过这些对象来
 * 执行实际的功能，例如调用方法，创建实例.
 * 
 * 使用反射生成并操作对象
 * 通过反射来生成对象由如下两种方式
 * 1.使用Class对象的newInstance方法来创建该Class对象对应类的实例.注：在很多JavaEE框架张都需要根据配置文件信息来创建那java对象，从配置文件中读取的只是某个类的字符串类名，程序需要根据该字符串来创建对应的实例，就必须使用反射.
 * 2.先使用Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance方法来创建该Class对象对应类的实例，通过这种方式可以选择指定的构造器来创建实例
 * @author WE
 *
 */
public class ObjectPoolFactory 
{
	//定义一个对象池
	private Map<String,Object> objectPool = new HashMap<>();
	//定义一个创建对象的方法，该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
	private Object createObject(String clazzName) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		//根据字符串来获取对应的Class对象
		Class<?> clazz = Class.forName(clazzName);
		//使用clazz对应类的的默认构造器创建实例
		return clazz.newInstance();
	}
	//该方法根据指定文件来初始化对象池，根据指定文件来创建对象
	public void initPool(String fileName) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		try {
			FileInputStream fis = new FileInputStream(fileName);
			Properties props = new Properties();
			props.load(fis);
			for(String name:props.stringPropertyNames())
			{
				//每取出一对key-value对，就根据value创建一个对象，调用createObject创建对象，并将对象添加到对象池中
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Object getObject(String name)
	{
		//从objectPool中取出指定name对应的对象
		return objectPool.get(name);
	}
	
	public static void main(String[] args) throws Exception{
		ObjectPoolFactory pf = new ObjectPoolFactory();
		pf.initPool("src/com/briup/reflect/obj.txt");
		System.out.println(pf.getObject("a"));
		System.out.println(pf.getObject("b"));
		
	}
	

}
