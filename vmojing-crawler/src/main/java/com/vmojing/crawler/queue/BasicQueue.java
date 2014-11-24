package com.vmojing.crawler.queue;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.mongodb.domain.Topic;

public class BasicQueue<T> {
	private static Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();
	private Queue<T> queue = new LinkedList<T>();

	protected Logger getLogger() {
		Class<?> type = this.getClass();
		if (!loggers.containsKey(type)) {
			Logger logger = LoggerFactory.getLogger(type);
			BasicQueue.loggers.put(type, logger);
		}
		return loggers.get(type);
	}
	public synchronized boolean push(T t) {
		return queue.offer(t);
	}
	public synchronized T pop() {
		return queue.poll();
	}
	public synchronized T top() {
		return queue.peek();
	}
	public synchronized int size() {
		return queue.size();
	}
	public synchronized boolean isEmpty() {
		return queue.isEmpty();
	}
	public synchronized boolean addAll(Collection<? extends T> t) {
		return queue.addAll(t);
	}
}
