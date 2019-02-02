package com.briup.thread;
/**
 * 线程的生命周期：当线程被创建并启动以后，它既不是一启动就进入了执行状态，也不是一直处于执行状态，在线程的声明周期中，它要经过新建，就绪，运行，阻塞和死亡5中状态，尤其是当线程启动以后，它不可能一直霸占着CPU独自运行，所以CPU需要在
 * 多个线程之间切换，于是线程状态也多次在运行，就绪之间切换。
 * 1.新建和就绪状态：
 * a:当程序使用new关键字创建了一个线程之后，该线程就处于新建状态，此时它和其它java对象一样，仅仅由Java虚拟机为其分配内存，并初始化其成员变量的值，此时的线程对象没有表现出任何的动态特征，程序也不会执行线程的线程执行体
 * b:当线程对象调用了start方法之后，该线程就处于就绪状态，java虚拟机会为其创建方法调用栈和程序计数器，处于这个状态中的线程并没有开始运行，只是表示该线程可以运行了，至于该线程何时开始运行，取决于JVM里线程调度器的调度
 * 注：如果直接调用run方法，则run方法立即就会被执行，在run方法返回之前，其它线程无法并发执行----也就是说如果直接调用调用线程对象的run方法，系统把线程对象当成一个普通对象，而run方法也是一个普通方法，而不是线程执行体.
 * 注2：如果直接调用run方法，则run方法里不能直接通过getName方法来获取当前线程的名字
 * 注3.调用线程的run方法之后，该线程已经不在处于新建状态，不要再次调用线程对象的start方法，否则将引发IllegalThreadStateException 
 * @author WE
 *
 */
public class InvokeRun extends Thread
{
	private int i;
	//重写run方法，run方法的方法体就是线程执行体
	public void run() 
	{
		for(;i<30;i++)
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
	public static void main(String[] args) 
	{
		for(int i=0;i<30;i++)
		{
			//获取主线程
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i==20)
			{
				//直接调用线程对象的run方法，系统会把线程对象当成普通对象，把run方法当成普通方法，所以下面两行代码并不会启动两个线程
				new InvokeRun().run();
				new InvokeRun().run();
				
			}
		}
		
	}


}
