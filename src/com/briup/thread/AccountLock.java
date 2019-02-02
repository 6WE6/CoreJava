package com.briup.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步锁：一种功能更强大的线程同步机制---通过显式定义同步锁对象来实现同步，在这种机制下，同步锁由Lock对象充当
 * Lock提供了比synchronized方法和synchronized代码块更广泛的锁定操作，Lock允许实现更灵活的结构，可以具有差别很大的属性，并且支持多个相关的Condition对象
 * 2.Lock时控制多个线程对共享资源进行访问的工具，通常，锁提供了对共享资源的独占访问，每次只能由一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象
 * 3.某些锁可能允许对共享资源进行并发访问;在实现线程安全的控制中，比较常用的时ReentranLock(可重入锁)，使用该Lock对象可以显式的加锁，释放锁。
 * a:定义锁对象
 * b:定义需要保证线程安全的方法 
 * @author WE
 *
 */
public class AccountLock {
	//定义锁对象
	private final ReentrantLock lock = new ReentrantLock();
	private String accountNo;
	private double balance;
	public AccountLock(){
		
	}
	public AccountLock(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	//因为账户余额不允许随便修改所以只为balance提供getter方法
	public double getBalance() {
		return balance;
	}
	//定义保证线程安全的方法
	public void draw(double drawAmount){
		//加锁
		lock.lock();
		try{
			
		if(balance>=drawAmount)
		{
			//吐出钞票
			System.out.println(Thread.currentThread().getName()+"取钱成功，吐出钞票："+drawAmount);
			try 
			{
				Thread.sleep(1);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			//修改账户余额
			balance -= drawAmount;
			System.out.println("\t余额为："+balance);
		}
		else
		{
			System.out.println(Thread.currentThread().getName()+"取钱失败！余额不足！");
		}
	}
	finally
	{
		//修改完毕，释放锁
		lock.unlock();
	}
	
}

}
