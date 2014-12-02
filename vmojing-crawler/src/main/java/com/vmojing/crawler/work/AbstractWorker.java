package com.vmojing.crawler.work;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.crawler.Contants;
import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.queue.TopicQueue;
import com.vmojing.crawler.work.push.PushStrategy;
import com.vmojing.mongodb.domain.Topic;

public abstract class AbstractWorker<T> implements Runnable {
	/*
	 * log
	 */
	private static Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();
	/*
	 * system varialbe
	 */
	private final long delayInInMilliseconds;
	private long lastSuccessfulTime;
	/*
	 * task queue
	 */
	private BasicQueue<T> queue ;
	private PushStrategy pushStrategy; // push strategy
	public AbstractWorker(BasicQueue<T> queue,PushStrategy pushStrategy) {
		this(Contants.delayInInMilliseconds,queue,pushStrategy);
	}
	public AbstractWorker(long delayInInMilliseconds,BasicQueue<T> queue,PushStrategy pushStrategy) {
		this.delayInInMilliseconds = delayInInMilliseconds;
		this.lastSuccessfulTime = System.currentTimeMillis();
		this.queue = queue;
		this.pushStrategy = pushStrategy;
	}
	protected abstract void work(T t);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			boolean running = true;
			while (!Thread.interrupted()) {
				delay();
				T t = null;
				synchronized (queue) {
					if(!queue.isEmpty()) {
						t = queue.pop();
						pushStrategy.push(queue, t);
					}else {
						getLogger().info("queue is empty,this thread will quit..");
						running = false;
					}
				}
				if(!running) break;
				if(t == null) {
					getLogger().error("线程运行出错, when pop element equal null.");
					break;
				}
				work(t);	
			}
			getLogger().info("线程真正退出了！");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			getLogger().error("Thread throw interruptException" +e);
		}finally{
			
		}
	}
	protected Logger getLogger(){
		Class<?> type = this.getClass();
		if(!loggers.containsKey(type)){
			Logger logger = LoggerFactory.getLogger(type);
			loggers.put(type, logger);
		}
		return loggers.get(type);
	}
	protected void delay() throws InterruptedException {
		while ((System.currentTimeMillis() - lastSuccessfulTime) < delayInInMilliseconds) {
			sleep();
		}
		lastSuccessfulTime = System.currentTimeMillis();
	}
	protected void sleep() throws InterruptedException {
		Thread.sleep(100);
	}
	protected void waitTime() throws InterruptedException{
		Thread.sleep(delayInInMilliseconds);
	}
}
