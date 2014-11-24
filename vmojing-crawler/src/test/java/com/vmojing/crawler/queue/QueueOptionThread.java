package com.vmojing.crawler.queue;

import com.vmojing.mongodb.domain.Topic;

public class QueueOptionThread extends Thread{
	protected String name;
	public QueueOptionThread(String name){
		this.name = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!TopicQueue.isEmpty()){
			System.out.println("the queue is no empty,I will pop the element in thread "+ name);
			sleep();
			Topic t = TopicQueue.pop();
			if(t == null){
				System.out.println("wow,error!!! in thread "+name);	
			}else{
				System.out.println("pop success!! in thread "+name);
			}
		}
		super.run();
	}
	protected void sleep(){
		
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
