package com.vmojing.crawler.queue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Topic;

/**
 * 话题队列
 * @author v11
 * @date 2014年11月13日
 * @version 1.0
 */
public class TopicQueue{
	private static Queue<Topic> queue = new LinkedList<Topic>();
	public synchronized static boolean push(Topic t){
		return queue.offer(t);
	}
	public synchronized static Topic pop(){
		return queue.poll();
	}
	public synchronized static Topic top(){
		return queue.peek();
	}
	public synchronized static int size(){
		return queue.size();
	}
	public synchronized static boolean isEmpty(){
		return queue.isEmpty();
	}
	public synchronized static boolean addAll(Collection<? extends Topic> t){
		return queue.addAll(t);
	}
}
