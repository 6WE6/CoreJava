package com.briup.collectioncore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1.Collections工具类操作(排序，查找，替换)List、Set、Map
 * 2.使用Collections工具类进行同步控制
 * 3.使用Collections工具类设置不可变集合
 * @author WE
 *
 */
public class CollectionsTest {
	public static void main(String[] args) {
		//使用如下方法对List集合元素进行排序
		ArrayList nums = new ArrayList();
		nums.add(2);
		nums.add(-5);
		nums.add(3);
		nums.add(0);
		System.out.println(nums);
		Collections.reverse(nums);//次序反转
		System.out.println(nums);
		Collections.sort(nums);//自然排序
		System.out.println(nums);
		Collections.shuffle(nums);//随机排序
		System.out.println(nums);
		//使用如下方法对集合元素进行查找，替换操作
		System.out.println(Collections.max(nums));
		System.out.println(Collections.min(nums));
		System.out.println(Collections.frequency(nums, -5));
		nums.add(-5);
		Collections.replaceAll(nums, -5, -4); 
		System.out.println(nums);
		//Collections类中提供了多个synchronizedXxx方法，该方法可以将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题
		//下面程序创建了4个线程安全的集合对象,直接将新创建的集合对象传给了Collections的synchronizedXxx方法
		Collection c = Collections.synchronizedCollection(new ArrayList());
		List list = Collections.synchronizedList(new ArrayList());
		Set s = Collections.synchronizedSet(new HashSet());
		Map m = Collections.synchronizedMap(new HashMap());
		//设置不可变集合，Collections提供了如下三类方法来返回一个不可变的集合，通过Collections提供的三类方法，可以生成“只读”的Collection或Map
		List unmodifyList = Collections.emptyList();
		Set unmodifyableSet = Collections.singleton("aaa");
		Map score = new HashMap(); 
		score.put("Chinese", 80);
		score.put("English", 100);
		Map unmmodifyableMap = Collections.unmodifiableMap(score);
//		unmodifyableSet.add("bbb");
//		unmodifyList.add("cccc");
//		unmmodifyableMap.put("math", 120);
		
		
	}

}
