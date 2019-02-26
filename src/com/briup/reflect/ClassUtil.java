package com.briup.reflect;

import java.lang.reflect.Method;

public class ClassUtil {
	/**
	 * 打印类的信息：成员变量，方法，构造器
	 * @param obj
	 */
	public static void printMethodMessage(Object obj){
		//要获取类的信息，要先获取类的类类型
		Class c = obj.getClass();
		//获取类的名称
		System.out.println("类的名称是："+c.getName());
		/**
		 * Method类，方法对象
		 */
		Method[] ms = c.getMethods();
		for(int i=0;i<ms.length;i++){
			//得到方法的返回值类型
			Class returnType = ms[i].getReturnType();
			System.out.println("方法的返回值类型："+returnType.getName());
			//得到方法得名字
			System.out.println(ms[i].getName()+"(");
			//得到方法得参数列表得类类型
			Class[] paramType = ms[i].getParameterTypes();
			for(Class c1:paramType){
				System.out.println("参数列表得类类型："+c1.getName());
			}
			System.out.println(")");
		}
	}

}
