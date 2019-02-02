package com.briup.thread;

import java.util.Date;

/**
 * 线程睡眠：如果让当前正在执行的线程暂停一段时间，并进入阻塞状态，则可以通过调用Thread类的静态sleep方法来实现.
 * 注：当当前线程调用sleep方法进入阻塞状态后，在其睡眠时间段内，该线程不会获得执行的机会，即使系统中没有其它可执行的线程，处于sleep的线程也不会执行，因此sleep方法常用来暂停程序的执行
 * @author WE
 *
 */
public class SleepTest {

	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			System.out.println("当前的时间为："+new Date());
			try {
				//输出两条字符串之间的时间间隔为1s
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
