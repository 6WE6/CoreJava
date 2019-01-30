package com.briup.collectioncore;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1.Queue集合用于模拟队列这种数据结构，验证常用方法。
 * 2.Queue集合提供了一个Deque接口，该接口提供了一些从两端来操作队列的元素的方法，Deque不仅可以当双端队列使用，还可以当栈用。ArrayDeque时Deque接口提供的基于数组典型实现类，创建Deque时同样可以指定一个参数
 * 用于指定Object[]数组的长度。
 * 3.LinkedList类不仅是List接口的实现类，还实现了Deque接口，因此既可以被当成栈来使用，也可以被当作双端队列来使用.ArrayList,ArrayDeque内部以数组的形式来保存集合中的元素，因此随机访问集合元素时,有较好的性能，而LinkedList
 * 内部以链表的形式来保存集合中的元素，因此插入，删除时性能较好
 * 4.关于List的总结：a:如果需要遍历List集合，对于ArrayList、Vector集合，应该使用随机访问方法（get）遍历集合元素，对于LinkedList集合，则因该采用迭代器来遍历集合元素。b:如果需要经常执行插入，删除操作，则可考虑使用LinkedList集合.
 * c:如果有多个线程需要同时访问list集合中的元素，可以使用Collections工具类将集合包装成线程安全的集合。
 * @author WE
 *
 */
public class QueueTest {

	public static void main(String[] args) {
		System.out.println("--------Queue集合中的常用方法----------");
		Queue que = new ArrayDeque();
		que.add(new String("哈哈"));
		que.add(new String("呵呵"));
		que.offer(new String("嘿嘿"));//当使用有容量限制的队列集合时，这种添加方法比较好
		System.out.println(que.peek());//获取队列头部元素，但不删除
		System.out.println(que.element());//获取队列头部元素，但不删除，如果此队列为空返回null
		System.out.println(que.poll());//获取队列头部元素，并删除
		System.out.println(que);
		System.out.println("---------------Deque接口中的常用方法-------------");
		//下面示范把ArrayDeque当作栈来使用
		ArrayDeque stack = new ArrayDeque();
		stack.push(new String("woaini"));
		stack.push(new String("niaiwo"));
		stack.push(new String("xiangqingxiangai"));
		System.out.println(stack);
		System.out.println(stack.peek());
		//下面示范把ArrayDeque当作队列使用
		ArrayDeque queue = new ArrayDeque();
		queue.offer("vue.js");
		queue.offer("jquery");
		queue.offer("bootstrap");
		System.out.println(queue);
		queue.getFirst();
		queue.offerFirst("html");
		System.out.println(queue);
		System.out.println("-------------------LinkedList-------------------");
		LinkedList ll = new LinkedList();
		ll.offer("aaa");
		ll.push("bbb");
		ll.offerFirst("cccc");
		//以List的方式来遍历集合元素
		for(int i = 0;i<ll.size();i++){
			System.out.println("遍历中："+ll.get(i));
		}
		System.out.println(ll.peekFirst());
		System.out.println(ll.pop());
		System.out.println(ll);
		
		
		
		
		
		
		
		

	}

}
