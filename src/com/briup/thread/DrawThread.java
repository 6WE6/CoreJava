package com.briup.thread;
public class DrawThread extends Thread{
	//模拟用户账户
	//private Account account;
	private AccountPlus account;
	//当前取钱线程所希望取得钱数
	private double drawAmount;
	public DrawThread(String name,AccountPlus account,double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	//当多个线程修改同一个共享数据时，将涉及数据安全问题
	/*不安全
	public void run(){
		//账户余额大于取钱数目
		if(account.getBalance()>=drawAmount){
			//吐出钞票
			System.out.println(getName()+"取钱成功，吐出钞票："+drawAmount);
		
		
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			//修改账户余额
			account.setBalance(account.getBalance()-drawAmount);
			System.out.println("\t余额为："+account.getBalance());
		}
		else{
			System.out.println(getName()+"取钱失败！余额不足！");
		}
	}
	*/
	
	/**
	 * 修改如下
	 * 同步监视器：
	 * 1.线程开始执行同步代码块之前，必须先获得对同步监视器的锁定
	 * 2.同步监视器的目的是阻止两个线程对同一个共享资源进行并发访问，因此通常推荐使用可能被并发访问的资源充当同步监视器
	 * 3.任何时刻只能有一个线程可以获得对同步监视器的锁定，当同步代码块执行完成后，该线程会立即释放对该同步监视器的锁定
	 * 注：任何线程在修改指定资源之前，首先对该资源加锁，在加锁期间其它线程无法修改该资源，当该线程修改完成后，该线程释放对该资源的锁定，通过这种方式就可以保证并发线程
	 * 在任一时刻只有一个线程可以进入修改共享资源的代码区（临界区）
	 */
	/*
	public void run(){
		
		//使用account作为同步监视器，任何线程在进入下面的同步代码块之前，必须先获得对同步监视器（account）的锁定----其它线程无法获得锁，也就无法修改
		//这种做法符合“加锁-->修改--->释放锁”的过程
		
		//账户余额大于取钱数目
		synchronized (account) 
		{
		if(account.getBalance()>=drawAmount)
		{
			//吐出钞票
			System.out.println(getName()+"取钱成功，吐出钞票："+drawAmount);
			try 
			{
				Thread.sleep(1);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		
			//修改账户余额
			account.setBalance(account.getBalance()-drawAmount);
			System.out.println("\t余额为："+account.getBalance());
		}
		else
		{
			System.out.println(getName()+"取钱失败！余额不足！");
		}
	}
		//同步代码块结束，该线程释放同步锁
	}
	*/
	
	//
	public void run(){
		//直接调用account对象的draw方法来执行取钱操作，同步方法的同步监视器是this,this代表调用draw方法的对象
		//也就是说，线程进入draw方法之前，必须先对account对象加锁
		account.draw(drawAmount);
	}
	

}
