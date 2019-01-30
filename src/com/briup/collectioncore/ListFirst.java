package com.briup.collectioncore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
/**
 * 1.List集合：在Collection接口的基础上，又提供了自己的方法.主要介绍ArrayList、Vector、ListIterator。
 * 2.List判断两个对象相等的标准：只要通过equals方法比较返回true即可。
 * 3.List还提供了一个listIterator()方法，该方法返回listIterator对象，ListIterator接口继承了Iterator接口，提供了专门操作List的方法。
 * 4.ArrayList和Vector类都是基于数组实现的List类，所以ArrayList和Vector类封装了一个动态的，允许再分配的Object数组，
 * ArrayList和Vector类使用initalCapacity参数来设置该数组的长度，当ArrayList或Vector中添加元素超过了该数组的
 * 长度时，它们的initialCapacity会自动增加，通常无须关心，但如果向ArrayList或Vector集合中添加大量元素时，可以使用
 * ensureCapacity(int minCapacity)方法一次性的增加initalCapacity，这可以减少重分配的次数，从而提高性能。ArrayList和Vector除了
 * ArrayList是线程不安全的，Vector则是线程安全的之外几乎相同。
 * 5.Vector提供了一个Stack子类,用于模拟栈这种数据结构。，但通常用ArrayDeque.
 * 6.操作数组的工具类Arrays，该工具类提供了asList(Object[] a)方法，该方法可以把一个数组或指定个数的对象转换成一个List集合，这个集合是Arrays的内部类ArrayList的实例,
 * Arrays.ArrayList是一个固定长度的List集合，程序只能遍历访问该集合里的元素，不可增加，删除该集合里的元素
 * 
 * @author WE
 *
 */
class Q{
	public boolean equals(Object obj){
		return true;
	}
}
public class ListFirst {

	public static void main(String[] args) {
		List books = new ArrayList();
		books.add(new String("java讲义"));
		books.add(new String("html讲义"));
		books.add(new String("Android讲义"));
		System.out.println(books);
		//插入元素到集合的index处
		books.add(1, new String("xml讲义"));
		System.out.println(books);
		for(int i=0;i<books.size();i++){
			System.out.println(books.get(i));
		}
		books.remove(2);
		System.out.println(books);
		//返回指定元素在List集合中的位置
		System.out.println(books.indexOf(new String("xml讲义")));
		//将index索引处的旧元素替换成xhtml讲义，并返回旧元素
		books.set(1, new String("xhtml讲义"));
	    System.out.println(books);
	    /**
	     * 验证List集合判断两个元素相等的标准
	     */
	    System.out.println("------------------------验证List集合元素相等的标准-----------------------");
	    List list = new ArrayList();
	    list.add(new String("aa"));
	    list.add(new String("bb"));
	    list.add(new String("cc"));
	    System.out.println(list);
	    //这里程序试图删除一个Q对象时，List将会调用该Q对象的equals方法依次与集合元素进行比较，如果该equals方法以某个集合元素作为参数时将返回true
	    list.remove(new Q());
	    System.out.println(list);
	    list.remove(new Q());
	    System.out.println(list);
	    System.out.println("----------------------ListIterator接口----------------------");
	    /**
	     * 验证ListIterator接口中提供的方法
	     */
	    String[] str = {"qqqqq","wwwwww","eeeee"};
	    List strList = new ArrayList();
	    for(int i=0;i<str.length;i++){
	    	strList.add(str[i]);
	    }
	    //获取ListIterator对象
	    ListIterator lit = strList.listIterator();
	    while(lit.hasNext()){
	    	System.out.println(lit.next());
	    	lit.add("-----分隔符------");
	    }
	    //注：在进行反向迭代时，要先进行正向迭代
	    System.out.println("下面开始反向迭代");
	    while(lit.hasPrevious()){
	    	System.out.println(lit.previous());
	    }
	    System.out.println("----------------Vector实现类--------------------");    
	    Stack sk = new Stack();
	    sk.push(new String("wang"));
	    sk.push(new String("en"));
	    System.out.println(sk.peek());
	    System.out.println(sk);
	    System.out.println(sk.pop());
	    System.out.println(sk);
	    System.out.println("----------------固定长度的List-------------------");
	    List fixedList = Arrays.asList("tangtang","wnagen");
	    //获取fixedList的实现类，将输出Arrays$ArrayList
	    System.out.println(fixedList.getClass());
	    System.out.println(fixedList);
	    //试图增加，删除将报错
	    fixedList.add(new String("tangen"));
	    
	}

}
