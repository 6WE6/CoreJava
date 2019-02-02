package com.briup.thread;
/**
 * 守护线程：在后台运行，它的任务是为其它线程提供服务，这种线程也被称为后台线程或精灵线程，JVM的垃圾回收线程就是典型的后台线程.
 * 特征：如果前台所有线程都已死亡，后台线程会自动死亡.
 * 用法：调用Thread的setDaemon(true)方法可以将指定线程设置为后台线程.
 * 拓展：当前台进程运行结束后，JVM主动退出，不管后台进程结束没结束，都要随前台进程的结束而结束；前台线程的子线程默认是前台进程，后台进程的子线程默认是后台进程；
 * 前台进程死亡后，JVM会通知后台进程死亡，但从它接收指令，到做出响应，需要一定时间；而且要将某个线程设置为后台线程，必须在该线程启动之前设置，否则会引发IllegalThreadException
 * @author WE
 *
 */
public class DaemonThread extends Thread
{
	//定义后台线程的线程执行体和普通线程没有任何区别
	public void run()
	{
		for(int i=0;i<30;i++)
		{
			System.out.println(this.getName()+" "+i);
		}
	}
	public static void main(String[] args) 
	{
		DaemonThread dt = new DaemonThread();
		//将此线程设置为后台线程
		dt.setDaemon(true);
		dt.start();
		for(int i=0;i<10;i++)
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
		//程序执行到此处，(main线程)前台线程结束，后台进程也随之结束
		
	}

}
