package com.briup.collectioncore;

import java.util.HashSet;
import java.util.Iterator;

/**
 * HashSet：特点：1.不能保证元素的排列顺序2.HashSet不是同步的3.集合元素值可以时null
 * 判断两个对象相等的标准：HashSet判断两个元素相等的标准是两个对象通过equals()方法比较相等，并且两个对象的hashCode方法返回值也相等
 * 重写hashCode()方法的基本准则：1.程序运行过程中，同一个对象多次调用hashCode()方法应返回相同的值2.两个对象通过equals方法比较返回true时，通过hashCode()方法也应返回相同的值
 * 3.对象中用作equals方法比较标准的实例变量，都应该用于计算HashCode值
 * @author WE
 *
 */

//类A的equals()方法总是返回true,但没有重写其hashCode方法
/**
 * 注：如果两个对象通过equals方法比较返回true,但两个对象的hashCode()方法返回不同的hashCode()值时，这将导致HashSet会把这两个对象保存在Hash表的不同位置
 * @author WE
 *
 */
class A{
	public boolean equals(Object object){
		return true;
	}
}
//类B的hashCode方法总是返回1，但没有重写其equals方法
/**
 * 如果两个对象通过HashCode方法比较返回相同的hashCode值，但通过equals方法比较返回false时，HashSet会把它们保存在同一个位置，使用链式结构来保存多个对象
 * @author WE
 *
 */
class B{
	public int hashCode(){
		return 1;
	}
}
//类C的hashCode方法总是返回2，equals方法总是返回true
class C{
	public int hashCode(){
		return 2;
	}
	
	public boolean equals(Object object){
		return true;
	}
}

//R类重写了equals方法和hashCode方法
class R{
	int count;
	public R(int count){
		this.count = count;
	}
	//重写Object类的toString方法,打印对象
	public String toString(){
		return "R[count:"+count+"]";
	}
	//重写Object类的equals方法
	public boolean equals(Object obj){
		//如果两个对象为同一个对象
		if(this==obj){
			return true;
		}
		//只有当obj是R类对象
		if(obj != null && obj.getClass() == R.class){
			R r =(R)obj;
			return this.count == r.count;
		}
		return false;
	}
	//重写hashCode方法
	public int hashCode(){
		return this.count;
	}
}
/**
 * 当向HashSet中添加可变对象时或者修改HashSet集合中的对象时，有可能导致该对象与集合中的其它对象相等，从而无法准确访问该对象
 * @author WE
 *
 */
public class HashSetTest { 

	public static void main(String[] args) {
//		HashSet books = new HashSet();
//		books.add(new A());
//		books.add(new A());
//		books.add(new B());
//		books.add(new B());
//		books.add(new C());
//		books.add(new C());
//		System.out.println(books);
		HashSet hs = new HashSet();
		hs.add(new R(5));
		hs.add(new R(-3));
		hs.add(new R(9));
		hs.add(new R(-2));
		System.out.println(hs);
		//取出第一个元素
		Iterator it =  hs.iterator();
		R first = (R)it.next();
		System.out.println(first);
		//为第一个元素的count实例变量赋值
		first.count = -3;
		System.out.println(hs);
		hs.remove(-3);
		System.out.println(hs);
	}

}
