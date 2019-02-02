package com.briup.thread;
/**
 * 线程同步：当使用多个线程来访问同一个数据时，很容易出现线程安全问题
 * 线程安全的经典问题：银行取钱问题
 * 1.用户输入用户名，密码，系统判断用户的账户，密码是否匹配
 * 2.用户输入取款金额
 * 3.系统判断账户余额是否大于取款金额
 * 4.如果账户余额大于取款金额，则取款成功；小于则取款失败
 * 模拟两个人使用同一个账户并发取钱的问题，忽略检查账户和密码的操作
 * @author WE
 * 账户类
 */
public class Account 
{
	private String accountNo;
	private Double balance;
	public Account(String accountNo, Double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public int hasCode(){
		return accountNo.hashCode();
	}
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj!=null&&obj.getClass()==Account.class){
			Account target = (Account)obj;
			return target.getAccountNo().equals(accountNo);
		}
		return false;
	}
	

}
