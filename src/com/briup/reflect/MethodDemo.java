package com.briup.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a1 = new A();
		a1.print(3, 5);
		System.out.println("=======================================");
		System.out.println("方法的反射操作：m.invoke(对象名，参数列表)");
		/**
		 * 获取方法名称和参数列表
		 */
		Class c1 = A.class;
		try {
			Method m =c1.getDeclaredMethod("print", int.class,int.class);
			try {
				m.invoke(a1, new Object[]{10,20});
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
class A{
	public void print(int a,int b){
		System.out.println(a+b);
	}
}
