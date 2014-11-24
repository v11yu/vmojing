package com.vmojing.crawler.work.push;

import com.vmojing.crawler.queue.BasicQueue;
/**
 * push queue 策略：放回原来的
 * @author v11
 * @date 2014年11月17日
 * @version 1.0
 */
public class OnePush implements PushStrategy{

	@Override
	public <T> void push(BasicQueue<T> queue, T obj) {
		// TODO Auto-generated method stub
		queue.push(obj);
	}

}
