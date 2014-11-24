package com.vmojing.crawler.work.push;

import com.vmojing.crawler.queue.BasicQueue;
/**
 * push queue 策略：不放置
 * @author v11
 * @date 2014年11月19日
 * @version 1.0
 */
public class NoPush implements PushStrategy{

	@Override
	public <T> void push(BasicQueue<T> queue, T obj) {
		// TODO Auto-generated method stub
		
	}

}
