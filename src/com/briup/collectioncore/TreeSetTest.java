package com.briup.collectioncore;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * TreeSet测试类：该类可以确保集合元素处于排序状态,采用红黑树的数据结构来存储数据元素，TreeSet支持两种排序方法，自然排序和定制排序，默认情况下，采用自然排序
 * 自然排序：TreeSet会调用集合元素的compareTo()方法来比较元素之间的大小关系，然后将集合元素按升序排列，这就是自然排序
 * 
 * TreeSet集合特点：TreeSet集合中的元素是有序的，所以增加了访问第一个，最后一个，前一个，后一个，以及截取TreeSet的方法
 * 判断两个对象相等的标准：两个对象通过compareTo()方法比较是否返回0.
 * 注：当需要把一个对象放入TreeSet集合中，重写该对象对应类的equals()方法时，应保证该方法与compareTo()方法有一致的返回结果
 * 如果向TreeSet中添加一个可变对象，并且后面程序修改了该可变对象的实例变量，这将导致它与其它对象的大小顺序发生改变
 * @author WE
 *
 */

/**
 * HashSet与TreeSet的比较：HashSet的性能总是比TreeSet好(特别时常用的添加，查询等操作)，只有当需要一个保持排序的Set时，才使用TreeSet.需要指出的时HashSet和TreeSet都是线程不安全的，必须通过Collections
 * 工具类的synchronizedSortedSet方法来包装该set集合
 * @author WE
 *
 */
//如果试图把一个对象添加到TreeSet时，则该对象的类必须实现Comparable接口
class Err{
	
}

class M{
	int age;
	public M(int age){
		this.age = age;
	}
	public String toString(){
		return "M[age:" + age + "]";
	}
}
public class TreeSetTest {

	public static void main(String[] args) {
		TreeSet nums = new TreeSet();
		//向TreeSet中添加四个Integer对象
		nums.add(5);
		nums.add(3);
		nums.add(10);
		nums.add(-9);
		//输出集合元素，看到集合元素处于排序状态
		System.out.println(nums);
		System.out.println(nums.first());
		System.out.println(nums.last());
		System.out.println(nums.lower(4));
		System.out.println(nums.subSet(3,11));
		/*
		 * 下面的代码运行将报错:
		 * 1.由于Err类并没有实现Comparable接口，所以添加两个对象进入集合当比较大小时，会引发ClassCastException
		 * 2.当TreeSet类调用conpareTo()方法时，需要将比较对象强转成相同类型，只有相同类的实例才会比较大小，即向TreeSet中添加的应该是同一个类的对象，否则也会引发ClassCastException
		 * 3.如果向TreeSet中添加的对象是程序员自定义类的对象，则可以向TreeSet中添加多种类型的对象
		 */
//		nums.add(new Err());
//		nums.add(new Err());
		/**
		 * TreeSet的定制排序:在是实现TreeSet的定制排序时，在创建TreeSet集合对象时，提供一个Comparator对象与该TreeSet集合关联，由该Comparator对象负责集合元素的排序逻辑
		 */
		
		TreeSet ts = new TreeSet(new Comparator() {
			public int compare(Object o1, Object o2) {
			    M m1 = (M)o1;
			    M m2 = (M)o2;
				return m1.age>m2.age ? -1 : m1.age<m2.age ? 1:0;
			}
		});
		ts.add(new M(5));
		ts.add(new M(-3));
		ts.add(new M(9));
		System.out.println(ts);
		

	}

}
