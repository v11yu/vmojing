package com.vmojing.crawler.queue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;

public class BloggerQueue {
	private static Queue<Blogger> queue = new LinkedList<Blogger>();
	public synchronized static boolean push(Blogger t){
		return queue.offer(t);
	}
	public synchronized static Blogger pop(){
		return queue.poll();
	}
	public synchronized static Blogger top(){
		return queue.peek();
	}
	public synchronized static int size(){
		return queue.size();
	}
	public synchronized static boolean isEmpty(){
		return queue.isEmpty();
	}
	public synchronized static boolean addAll(Collection<? extends Blogger> c){
		return queue.addAll(c);
	}
}
