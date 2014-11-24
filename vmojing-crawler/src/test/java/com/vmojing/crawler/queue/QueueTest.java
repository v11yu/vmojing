package com.vmojing.crawler.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vmojing.mongodb.domain.Topic;

public class QueueTest {
	@Before
	public void init(){
		TopicQueue.push(new Topic());
	}
	@After
	public void after(){
		while(!TopicQueue.isEmpty()) TopicQueue.pop();
	}
	@Test
	public void testMultiplePop(){
		for(int i=0;i<10;i++){
			QueueOptionThread qt = new QueueOptionThread("Toby"+i);
			qt.start();
		}
	}
	@Test
	public void testMultiplePop2(){
		for(int i=0;i<10;i++){
			QueueOptionThread qt = new QueueOptionSafeThread("Toby"+i);
			qt.start();
		}
	}
	public static void main(String[] args) {
		QueueTest t = new QueueTest();
		t.init();
		t.testMultiplePop2();
	}
}
