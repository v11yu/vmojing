package com.vmojing.crawler.queue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;

public class ClueQueue {
	private static Queue<Clue> queue = new LinkedList<Clue>();
	public synchronized static boolean push(Clue t){
		return queue.offer(t);
	}
	public synchronized static Clue pop(){
		return queue.poll();
	}
	public synchronized static Clue top(){
		return queue.peek();
	}
	public synchronized static int size(){
		return queue.size();
	}
	/**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
	public synchronized static boolean isEmpty(){
		return queue.isEmpty();
	}
	public synchronized static boolean addAll(Collection<? extends Clue> c){
		return queue.addAll(c);
	}
}
