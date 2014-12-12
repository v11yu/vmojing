package com.vmojing.crawler.work;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.vmojing.crawler.CrawlerConfig;
import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.work.check.CheckStrategy;
import com.vmojing.crawler.work.push.PushStrategy;
import com.vmojing.mongodb.annotation.Frequent;
import com.vmojing.mongodb.annotation.LastTime;
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
	protected final static long minute = 1000*60;
	/*
	 * task queue
	 */
	private BasicQueue<T> queue ;
	private PushStrategy pushStrategy; // push strategy
	private CheckStrategy checkStrategy;
	
	public AbstractWorker(BasicQueue<T> queue,PushStrategy pushStrategy,CheckStrategy checkStrategy) {
		this(CrawlerConfig.getNum("WorkerDelayTime")*minute,queue,pushStrategy,checkStrategy);
	}
	public AbstractWorker(long delayInInMilliseconds,BasicQueue<T> queue,PushStrategy pushStrategy,CheckStrategy checkStrategy) {
		this.delayInInMilliseconds = delayInInMilliseconds;
		this.lastSuccessfulTime = new Date(0).getTime();
		this.queue = queue;
		this.pushStrategy = pushStrategy;
		this.checkStrategy = checkStrategy;
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
				if(checkStrategy.check(t)){
					work(t);
				}
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
	/**
	 * 每次work之前，至少间隔的时间
	 * 在Contants里配置
	 * 默认1分钟
	 * @throws InterruptedException
	 */
	protected void delay() throws InterruptedException {
		while ((System.currentTimeMillis() - lastSuccessfulTime) < delayInInMilliseconds) {
			sleep();
		}
		lastSuccessfulTime = System.currentTimeMillis();
	}
	protected void sleep() throws InterruptedException {
		Thread.sleep(100);
	}

}
