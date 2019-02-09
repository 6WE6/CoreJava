package com.briup.reflect;

import java.lang.reflect.Constructor;
/**
 * 通常在开发通用性比较广的框架，基础平台时可能会大量使用反射
 * @author WE
 *
 */
public class CreateJFrame {
	public static void main(String[] args) throws Exception 
	{
		//获取JFrame对应的Class对象
		Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
		//获取JFrame中带一个字符串参数的构造器
		Constructor ctor = jframeClazz.getConstructor(String.class);
		//调用Constructor的newInstance方法创建对象
		Object obj = ctor.newInstance("测试窗口");
		//输出JFrame
		System.out.println(obj);
		
	}

}
