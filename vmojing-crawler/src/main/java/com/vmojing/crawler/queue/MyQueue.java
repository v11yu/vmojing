package com.vmojing.crawler.queue;

import java.util.LinkedList;
import java.util.Queue;

import com.vmojing.mongodb.domain.Topic;

public class MyQueue<T> {
	private Queue<T> queue = new LinkedList<T>();
	public synchronized boolean push(T t){
		return queue.offer(t);
	}
	public synchronized T pop(){
		return queue.poll();
	}
	public synchronized T top(){
		return queue.peek();
	}
	public synchronized int size(){
		return queue.size();
	}
	public synchronized boolean isEmpty(){
		return queue.isEmpty();
	}
}
