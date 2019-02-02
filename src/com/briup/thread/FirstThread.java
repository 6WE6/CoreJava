package com.briup.thread;
/**
 * 1.任务--->程序--->进程--->线程
 * 注：操作系统可以同时运行多个任务，一个任务通常就是一个程序，每个运行中的程序就是一个进程，而当一个程序运行时，内部可能包含了多个顺序执行流，每个顺序执行流就是一个线程；
 * 同一个进程的多个线程之间共享父进程里的全部资源，包括进程代码段，进程的公有数据等；一个线程可以创建和撤销另一个线程，同一个进程中的多个线程之间可以并发执行；线程的调度和管理由进程本身负责完成；
 * 2.Java使用Thread类代表线程，所有的线程对象都必须是Thread类或其子类的实例，每个线程的作用是完成一定的任务，通过继承Thread类来创建并启动多线程的步骤如下：
 * a:定义Thread类的子类，并重写该类的run方法，该run方法的方法体就代表了线程需要完成的任务，因此把run方法称为线程执行体
 * b:创建Thread子类的实例，即创建了线程对象
 * c:调用线程对象的start方法来启动该线程
 * 3.进行多线程编程时不要忘记Java程序运行时默认的主线程，main方法的方法体就是主线程的线程执行体
 * 4.使用继承Thread类的方法来创建线程时，多个线程之间无法共享线程类的实例变量
 * @author WE
 *
 */
public class FirstThread extends Thread{
	private int i;//程序每次创建线程时都需要创建一个FirstThread对象，所以不能共享该实例变量
	//重写run方法，run方法的方法体就是线程执行体
	public void run()
	{
		for(i=0;i<30;i++)
		{
			//当线程类继承Thread类时，直接使用this即可获取当前线程；Thread对象的getName()返回当前线程的名字
			System.out.println(this.getName()+" " + i);
		}
	}
	public static void main(String[] args) 
	{
		for(int i=0;i<30;i++)
		{
			//调用Thread的currentThread方法获取当前线程
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i==20)
			{
				//两个线程的循环变量不连续，表明它们没有共享数据
				//创建并启动第一个线程
				new FirstThread().start();
				//创建并启动第二个线程
				new FirstThread().start();
				
			}
			
			
		}
		
	}
	

}
