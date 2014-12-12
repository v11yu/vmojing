package com.vmojing.crawler.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vmojing.crawler.queue.BasicQueue;

public abstract class Client {
	private static Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();
	/**
	 * 处理任务
	 */
	abstract void work();
	/**
	 * 初始化
	 */
	abstract void initialize();
	/**
	 * 结束收回
	 */
	abstract void after();
	/**
	 * 装配队列
	 * @param queue 队列
	 * @param ls 列表
	 */
	abstract <T> void fillQueue(BasicQueue<T> queue, List<T> ls);
	/**
	 * 启动client运行
	 */
	public void start(){
		initialize();
		getLogger().info("初始化完毕");
		work();
		getLogger().info("一轮结束");
		after();
	}
	protected Logger getLogger(){
		Class<?> type = this.getClass();
		if(!loggers.containsKey(type)){
			Logger logger = LoggerFactory.getLogger(type);
			loggers.put(type, logger);
		}
		return loggers.get(type);
	}
	protected void waitThread(ThreadPoolTaskExecutor taskExecutor) {
		while (taskExecutor.getActiveCount() > 0) {
			getLogger().info("thread active count:" + taskExecutor.getActiveCount());
			sleep(30);
		}
	}
	protected void sleep(int second){
		try {
			Thread.sleep(1000*second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
