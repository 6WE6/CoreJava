package com.briup.thread;
/**
 * 1.同步方法：与同步代码块对应，Java的多线程安全支持同步方法，同步方法就是使用synchronized关键字来修饰某个方法，该方法称为同步方法；对于synchronized修饰的实例方法（非static方法）而言，无须显式指定同步监视器，同步方法的同步监视器
 * 是this,也就是调用该方法的对象；通过使用同步方法可以非常方便的实现线程安全的类；
 * 2.线程安全的类具有如下特征：
 * a:该类的对象可以被多个线程安全的访问
 * b:每个线程调用该对象的任意方法之后，该对象状态依旧保持合理状态
 * c:每个线程调用该对象的任意方法之后，都将得到正确的结果
 * 注：关于可变类与不可变类，其中不可变类总是线程安全的，因为它的对象状态不可改变，但可变对象需要额外的方法来保证其线程安全，e.g:银行取钱的例子中，当两个线程同时修改Account对象的balance成员变量的值时，程序就出现了异常，下面
 * 将AccountPlus类对balance的访问设置成线程安全的，那么只要把修改balance的方法变成同步方法即可.
 * 注2：可变类的线程安全是以降低程序的运行效率为代价的，为了减少线程安全所带来的负面影响，程序可以采用如下策略：a:不要对线程类的所有方法都进行同步，只对那些会改变竞争资源（竞争资源也是共享资源）的方法同步，例如AccountPlus类的
 * accountNo实例变量就无需同步；b:如果可变类有两种运行环境，单线程环境和多线程环境，则应该为该可变类提供两种版本，即线程不安全版本和线程安全版本，在单线程环境中使用线程不安全版本以保证性能，在多线程环境中使用线程安全版本；StringBuffer就是为照顾
 * 单线程环境和多线程环境所提供的类，在单线程环境下因该使用StringBuilder来保证较好的性能；当需要保证多线程安全时，就应该使用StringBuffer
 * @author WE
 *
 */

public class AccountPlus 
{
	private String accountNo;
	private double balance;
	public AccountPlus(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	//因为账户余额不允许随便修改，所以只为balance提供getter方法
	public double getBalance() {
		return balance;
	}
	//提供一个线程安全的draw方法来完成取钱操作
	//该同步方法的同步监视器是this
	public synchronized void draw(double drawAmount){
		//如果账户余额大于取钱数目
		if(balance>=drawAmount){
			//吐出钞票
			System.out.println(Thread.currentThread().getName()+"取钱成功，吐出钞票： "+drawAmount);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//修改余额
		balance -= drawAmount;
		System.out.println("\t余额为："+balance);
		}
		else{
			System.out.println(Thread.currentThread().getName()+"取钱失败，余额不足！");
		}
	}
	
	

}
