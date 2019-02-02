package com.briup.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程通信的方式：程序通常无法准确控制线程的轮换执行，但Java也提供了一些机制来保证线程的协调运行
 * 方式1：借助于Object类的提供的wait方法，notify方法，notifyAll方法，这三个方法必须由同步监视器对象来调用
 * 方式2：使用Condition类控制线程通信，当使用Lock对象来保证同步时，Java提供了一个Condition类来保持协调，使用Condition可以让那些已经得到Lock对象却无法继续执行的的线程释放Lock对象，Condition对象也可以唤醒其它处于等待的线程；
 * Condition将同步监视器方法(wait,notify,notifyAll)分解成截然不同的对象，以便通过将这些对象与Lock对象组合使用，在这种情况下，Lock替代了同步方法或同步代码块，Condition替代了同步监视器的功能；Condition实例被绑定在一个Lock对象上，
 * 要获得特定Lock实例的Condition实例，调用Lock对象的newCondition方法即可
 * (await,signal,signalAll)
 * 
 * 线程池：由于启动一个新线程的代价较高，当程序中需要创建大量生存期很短暂的线程时，应该考虑使用线程池，线程池在系统启动时即创建大量空闲的线程，程序将一个Runnable对象或Callable对象传给线程池，线程池就会启动一个线程来执行它们的run方法或call方法
 * ，当run方法或call方法执行结束后，该线程并不会死亡，而是再次返回线程池中成为空闲状态，等待执行下一个Runnable对象的run方法或call方法；使用线程池可以有效控制系统中并发线程的数量，当系统中包含大量并发线程时，会导致系统性能下降，甚至导致JVM崩溃
 * 而线程池的最大线程数参数可以控制系统中并发线程数不会超过此数；通过Executors工厂类来产生线程池，该工厂类包含7个静态工厂方法来创建线程池；用完一个线程池后，应该调用该线程池的shutdown方法，该方法将启动线程池的关闭序列，调用shutdown方法的后的
 * 线程池不在接收新任务，但会将以前所有已提交任务执行完成，当线程池中的所有任务都执行完成后，线程池中的所有线程死亡；另外也可以调用线程池的shutdownNow方法来关闭线程池，该方法试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回正在等待执行的
 * 任务列表。
 * 使用线程池来执行线程任务的步骤如下：
 * 1.调用Executors类的静态工厂方法创建一个ExecutorService对象，该对象代表一个线程池
 * 2.创建Runnable实现类或Callable实现类的实例，作为线程执行任务
 * 3.调用ExecutorService对象的submit方法来提交Runnable实例或Callable实例
 * 4.当不想提交任何任务时，调用ExecutorService对象的shutdown方法来关闭线程池
 * 
 * @author WE
 *
 */
public class ThreadPoolTest implements Runnable{
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+"的i值为： "+i);
		}
	}
	public static void main(String[] args) {
		//创建一个具有固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(6);
			for(int i=0;i<100;i++){
				System.out.println(Thread.currentThread().getName()+"的i值为： "+i);
			}
		//创建runnable实现类的对象
		ThreadPoolTest tpt = new ThreadPoolTest();
		Thread th1 = new Thread(tpt,"线程1");
		Thread th2 = new Thread(tpt,"线程2");
		pool.submit(th1);
		pool.submit(th2);
		//关闭线程池
		pool.shutdown();

	}

}
