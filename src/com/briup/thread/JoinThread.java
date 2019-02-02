package com.briup.thread;
/**
 * 控制线程：java的线程支持提供了一些便捷的工具方法，通过这些便捷的工具方法可以很好的控制线程的执行
 * 1.join线程：Thread提供的让一个线程等待另一个线程完成的方法--join方法
 * 注1：当某个线程执行流中调用其它线程的join方法时，调用线程将被阻塞，直到被join方法加入的join线程执行完为止
 * 注2：join方法通常由使用线程的程序调用，以将大问题划分成许多小问题，每个小问题分配一个线程，当所有的小问题都得到处理后，在调用主线程来进一步操作
 * @author WE
 *
 */
public class JoinThread extends Thread{
	//提供一个有参数的构造器，用于设置该线程的名字
	public JoinThread(String name){
		super(name);
	}
	//重写run方法，定义线程执行体
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println(getName()+" "+i);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		//启动子线程
		new JoinThread("新线程").start();
		for(int i=0;i<30;i++){
			if(i==20){
				JoinThread jt = new JoinThread("被Join的线程");
				jt.start();
				//main线程调用了jt线程的join方法，main线程必须等jt执行结束后才会向下执行
				jt.join();
			}
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
}
