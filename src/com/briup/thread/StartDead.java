package com.briup.thread;
/**
 * 线程的运行和阻塞状态以及线程死亡
 * 1.线程的运行：如果处于就绪状态的线程获得了CPU,开始执行run方法的线程执行体，则该线程处于运行状态；对抢占式的系统而言，线程的处理任务的时间基于时间片，选择下一个线程时，系统会考虑线程的优先级
 * 2.当发生如下情况时，线程将会进入阻塞状态：a:线程调用sleep方法主动放弃所占用的处理器资源；b:线程调用了一个阻塞式IO方法，在该方法返回之前，该线程被阻塞;c:线程试图获得一个同步监视器，但该同步监视器正在被其他线程所持有；
 * d:线程在等待某个通知（notify）;e:程序调用了线程的suspend()方法将该线程挂起，但该方法容易导致死锁，所以因该尽量避免使用该方法；
 * 注1：当前正在执行的线程被阻塞之后，其它线程就可以获得执行机会，被阻塞的线程会在合适的时候重新进入就绪状态
 * 注2：当发生如下特定的情况时可以解除上面的阻塞，让该线程重新进入就绪状态：a:调用sleep方法的线程经过了指定时间；b:线程调用的阻塞式IO方法已经返回；c:线程成功获得了试图取得的同步监视器
 * d:线程正在等待某个通知时，其它线程发出了一个通知；e:处于挂起状态的线程被调用了resume方法
 * 3.线程死亡：线程会以如下三种方式结束，结束后就处于死亡状态.a:run或call方法执行完成，线程正常结束；b:线程抛出一个未捕获的Exception或Error；c:直接调用该线程的stop方法来结束该线程，该方法容易导致死锁，
 * 通常不推荐使用。
 * 注1：当主线程结束时，其它线程不受任何影响，并不会随之结束，一旦子线程启动起来后，它就拥有和主线程相同的地位，它不会受主线程的影响
 * 
 * @author WE
 *
 */
public class StartDead extends Thread{
	private int i;
	public void run(){
		for(;i<30;i++){
			System.out.println(this.getName()+" "+i);
		}
	}
	public static void main(String[] args) {
		StartDead sd = new StartDead();
		for(int i=0;i<30;i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
		if(i==20){
			sd.start();
			System.out.println(sd.isAlive());
		}
		//当线程处于新建，死亡两种状态时，isAlive方法返回false,当i>20时，该线程必定已经启动过了，如果sd.isAlive为假时，那就是死亡状态了
		if(i>20 && !sd.isAlive()){
			//试图再次启动线程
			//sd.start();
		}
		}
		

	}

}
