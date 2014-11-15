package com.vmojing.crawler.queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.mongodb.domain.Topic;

public abstract class AbstractQueue {
	private static Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();
	private static Map<Class<?>,Queue<?>> queues = new HashMap<Class<?>, Queue<?>>();
	protected Logger getLogger(){
		Class<?> type = this.getClass();
		if(!loggers.containsKey(type)){
			Logger logger = LoggerFactory.getLogger(type);
			AbstractQueue.loggers.put(type, logger);
		}
		return loggers.get(type);
	}
	protected Queue<?> getQueue(){
		Queue<Topic> queue = new LinkedList<Topic>();
		return queue;
	}
}
