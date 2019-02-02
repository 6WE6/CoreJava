package com.briup.thread;
/**
 * yield方法：Thread类提供的静态方法，可以让当前正在执行的线程暂停，但它不会阻塞该线程，只是将该线程转入就绪状态，yield方法只是让当前线程暂停一下，让系统的线程调度器重新调度一下
 * 注1：当某个线程调用了yield方法暂停之后，只有优先级与当前线程相同，或者优先级比当前线程更高的处于就绪状态的线程才会获得执行的机会,因此当某个线程调用了yield方法暂停之后，线程调度器又
 * 将其调度出来重新执行
 * 线程的优先级：每个线程执行时都具有一定的优先级，优先级高的线程获得较多的执行机会，而优先级低的线程获得较少的执行机会；每次线程默认的优先级都与创建它的父线程的优先级相同，默认情况下，main线程具有普通优先级，由main线程创建的子线程
 * 也具有普通优先级 
 * @author WE
 *
 */
public class YieldTest extends Thread
{
	public YieldTest(String name)
	{
		super(name);
	}
	public void run()
	{
		for(int i=0;i<30;i++)
		{
			System.out.println(getName()+" "+i);
			if(i==20)
			{
				Thread.yield();
			}
		}
	}
	public static void main(String[] args) 
	{
		//启动两个并发线程
		YieldTest yt1 = new YieldTest("高级");
		//将yt1线程设置成最高优先级
		yt1.setPriority(Thread.MAX_PRIORITY);
		yt1.start();
		YieldTest yt2 = new YieldTest("低级");
		//将yt2线程设置为最低优先级
		yt2.setPriority(Thread.MIN_PRIORITY);
		yt2.start();

	}

}
