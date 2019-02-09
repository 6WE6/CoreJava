package com.briup.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * 1.当获得某个类对应的Class对象后，就可以通过该Class对象的getMethods方法或者getMethod方法来获取全部方法或指定方法----这两个方法的返回值是Method数组，或者Method对象；每个Method对象对应一个方法，获得Method对象后，程序就可通过该Method
 * 来调用它对应的方法，在Method里包含一个invoke方法
 * 2.通过Class对象的getFields或getField方法可以获取该类所包含的全部成员变量或指定成员变量
 * 3.在java.lang.reflect包下还提供了一个Array类，Array对象可以代表所有的数组，程序可以通过使用Array来动态的创建数组，操作数组元素
 * 
 * @author WE
 *
 */
class Person
{
	private String name;
	private int age;
	public String toString()
	{
		return "Person[name:"+name+",age:"+age+"]";
	}
}
public class FieldTest 
{
	public static void main(String[] args) throws Exception
	{
		Person p = new Person();
		Class<Person> personClazz = Person.class;
		//获取Person的名为name的成员变量，使用getDeclaredField方法表明可获取各种访问控制符的成员变量
		Field nameField = personClazz.getDeclaredField("name");
		//设置通过反射访问该成员变量时取消访问权限检查
		nameField.setAccessible(true);
		//调用set方法为p对象的name成员变量设置值
		nameField.set(p, "wangen");
		//获取Person类名为age的成员变量
		Field ageField = personClazz.getDeclaredField("age");
		//设置通过反射访问该成员变量时取消访问权限检查
		ageField.setAccessible(true);
		//调用setInt方法为p对象的age成员变量设置值
		ageField.setInt(p, 30);
		System.out.println(p);
		System.out.println("---------------------------------------------");
		//创建一个元素类型为String，长度为10的数组
		Object arr = Array.newInstance(String.class, 10);
		//为arr数组中index为5的元素赋值
		Array.set(arr, 5, "woaitang");
		//取出arr数组中index为5的元素的值
		Object book = Array.get(arr, 5);
		System.out.println(book);
		
		
		
	}


}
