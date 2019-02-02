package com.briup.thread;

/**
 *1.实现Runnable接口创建线程类，步骤如下：
 * a:定义Runnable接口的实现类，并重写该接口的run方法，该run方法的方法体同样是该线程的线程执行体
 * b:创建runnable接口实现类的实例，并以此实例作为Thread的target来创建Thread对象，该Thread对象才是真正的线程对象
 * c:调用线程对象的start方法来启动该线程
 *2.采用Runnable接口的方式创建的多个线程可以共享线程类的实例变量，这是因为在这种方式下，程序创建的Runnable对象只是线程的target,而多个线程可以共享同一个target,所以
 *多个线程可以共享同一个线程类的实例变量。
 * @author WE
 *
 */
public class SecondThread implements Runnable
{
	private int i;
	//run方法同样是线程执行体
	public void run()
	{
		for(;i<30;i++)
		{
			//当线程类实现Runnable接口时，如果想获取当前线程，只能用Thread.currentThread()方法
			System.out.println(Thread.currentThread().getName()+" " +i);
		}
	}
	public static void main(String[] args) 
	{	
		for(int i=0;i<30;i++)
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i==20)
			{
				SecondThread st = new SecondThread();
				//通过new Thread(target,name)的方式创建新线程
				new Thread(st, "新线程1").start();
				new Thread(st, "新线程2").start();
				
			}
			
		}
		
		
		
		

	}

}
