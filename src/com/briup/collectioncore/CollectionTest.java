package com.briup.collectioncore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合体系：java的集合类主要由两个接口派生而出：Collection和Map
 * 主要掌握Set集合下的HashSet、TreeSet，List集合下的ArrayList、LinkedList和Vector，Queue下的ArrayQueue，LinkedList,Map下的HashMap,HashTable,TreeMap等实现类
 * @author WE
 *
 * 测试Collection集合和遍历Collection集合
 */
public class CollectionTest {
	

	public static void main(String[] args) {
		Collection<String> c = new ArrayList<>();
		c.add("TT");
		c.add("WW");
		System.out.println(c.size());
		System.out.println(c.contains("TT"));
		c.remove("WW");
		System.out.println(c.size());
		System.out.println(c.isEmpty());	
		c.add("JJ");
		c.add("QQ");
		//利用Collection接口提供的iterator()方法获取iterator对象，来迭代访问集合元素,当使用Iterator对象来迭代访问Collection集合元素时,Collection集合里的元素不能被改变，只有通过Iterator对象提供的remove方法
		//返回上次next方法返回的元素
		Iterator<String> it = c.iterator();
		while(it.hasNext()){
			String str = (String)it.next();//返回集合里的下一个元素，next()方法返回的是Object类型的数据，因此需要强制类型转换
			System.out.println(str);
			if(str.equals("QQ")){
				it.remove();
			}
			//由于遍历集合时，Iterator对象并不是把集合元素本身传递给了迭代变量，而是把集合元素的值传递给了迭代变量，所以修改迭代变量的值对集合没有影响
			str = "YY";
		}
		c.add("QQ");
		//Collection集合里可以包含重复的元素
		c.add("TT");
		c.add(null);
		System.out.println(c);
		//使用foreach循环遍历和Iterator类似，同样不允许修改
		for(String aa : c){
			System.out.println(aa);
//			if(aa.equals("QQ")){
//				c.remove("QQ");这里的修改操作将报错
//			}
//			System.out.println(c);
		}
		
		

	}

}
