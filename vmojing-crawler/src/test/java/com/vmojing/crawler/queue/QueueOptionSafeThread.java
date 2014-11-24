package com.vmojing.crawler.queue;

import com.vmojing.mongodb.domain.Topic;

public class QueueOptionSafeThread extends QueueOptionThread{

	public QueueOptionSafeThread(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (TopicQueue.class) {
			if (!TopicQueue.isEmpty()) {
				System.out
						.println("the queue is no empty,I will pop the element in thread "
								+ name);
				sleep();
				Topic t = TopicQueue.pop();
				if (t == null) {
					System.out.println("wow,error!!! in thread " + name);
				} else {
					System.out.println("pop success!! in thread " + name);
				}
			}
		}
	}
}
