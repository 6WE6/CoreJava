package com.briup.collectioncore;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 1.Map集合用于保存具有映射关系的数据(key-value),key集和Set集合相似，value集和List集合相似。验证Map里的常用方法。
 * 2.HashMap与Hashtable(较古老)实现类：Hashtable是一个线程安全的实现类，HashMap是线程不安全的实现；Hashtable不允许使用null作为key和value,HashMap可以使用null作为key和value,但放入HashMap中的
 * key不能重复，可以有多个value为空;为了成功在HashMap,Hashtable中存储获取对象，用作key的对象必须实现hashCode()方法和equals方法，HashMap,Hashtable判断两个key相等的标准是通过equals方法比较返回true，两个key
 * 的HashCode也值相等，判断两个value值相等标准是，通过equals方法比较返回true;与HashSet类似的是，如果使用可变对象作为HashMap,HashTable的key,并且程序修改了作为key的可变对象，则可能出现程序再也无法准确访问到Map中被修改的key
 * 
 * 
 * 
 * @author WE
 *
 */
class MM{
	int count;
	public MM(int count){
		this.count = count;
	}
	//根据count的值来判断两个对象是否相等
	public boolean equals(Object obj){
		if(obj==this){
			return true;
		}
		if(obj!=null && obj.getClass() == MM.class){
			MM a = (MM)obj;
			return this.count==a.count;
		}
		return false;
	}
	public int hashCode(){
		return this.count;
		
	}
}
class AA{
	public boolean equals(Object obj){
		return true;
	}
}
public class MapTest {

	public static void main(String[] args) {
		System.out.println("-------------Map集合的方法验证-------------");
		Map map = new HashMap();
		map.put(1, "java");
		map.put(2, "html");
		map.put(3, "css");
		map.put(2, "xml");
		System.out.println(map);
		System.out.println(map.keySet());
		map.remove(2);
		System.out.println(map);
		System.out.println(map.containsKey(3));
		map.put(2, "大数据");
		map.put(4, "机器语言");
		System.out.println(map);
		for(Object k:map.keySet()){
			System.out.println(map.get(k));
		}
		System.out.println("-----------------HashMap与Hashtable-----------------");
		Hashtable ht = new Hashtable();
		ht.put(new MM(6), "hello");
		ht.put(new MM(9), "hihi");
		ht.put(new MM(12), new AA());
		System.out.println(ht);
		System.out.println(ht.containsValue("afdfadsfd"));
		
		
		
		

	}

}
