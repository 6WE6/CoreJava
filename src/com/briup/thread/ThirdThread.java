package com.briup.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable和Future创建线程
 * 1.通过实现Runnable接口创建多线程时，Thread类的作用就是把run方法包装成线程执行体，那么是否可以把任意方法都包装成线程执行体呢？-->于是java提供了Callable接口，Callable
 * 接口提供了一个call方法可以作为线程执行体，call方法比run方法功能跟强大，可以有返回值，可以声明抛出异常，因此可以提供一个Callable对象作为Thread的target,而该线程的线程执行
 * 体就是Callable对象的call方法（注意Callable对象不能直接作为Thread的target）;call方法有一个返回值，call方法并不是直接调用，而是作为线程执行体被调用，如何获取call方法的返回值呢？——>
 * 通过Future接口来代表Callable接口里call方法的返回值，并为Future接口提供了一个FutureTask实现类，该实现类实现了Future接口和Runnable接口，可以作为Thread的target
 * 2.创建并启动有返回值的线程的步骤如下
 * a:创建Callable接口的实现类，并实现call方法，该call方法将作为线程执行体，且该call方法有返回值；在创建callable实现类的实例
 * b:使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call方法的返回值
 * c:使用FutureTask对象作为Thread对象的target创建并启动新线程
 * d:使用FutureTask对象的get方法来获得子线程执行结束后的返回值
 * 3.注Callable接口有泛型限制，Callable接口里的泛型形参类型与call方法返回值类型相同
 * @author WE
 *
 */
public class ThirdThread implements Callable
{
	public Object call() throws Exception {
		int i=0;
		for(;i<30;i++)
		{
			System.out.println(Thread.currentThread().getName()+"的循环变量i的值： " +i);
		}
		return i;
	}
	public static void main(String[] args) 
	{
		//创建Callable对象
		ThirdThread rt = new ThirdThread();
		//先使用匿名内部类创建Callable<Integer>对象，使用FutureTask来包装Callable对象
		FutureTask<Integer> task = new FutureTask<Integer>(rt);
		for(int i=0;i<30;i++){
			System.out.println(Thread.currentThread().getName()+" 的循环变量i的值：" +i);
			if(i==20){
				//实质还是以Callable对象来创建并启动线程的
				new Thread(task,"有返回值的线程").start();
			}
			try {
				System.out.println("子线程的返回值："+task.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}


}
