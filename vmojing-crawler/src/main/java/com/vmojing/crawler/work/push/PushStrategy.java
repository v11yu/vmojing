package com.vmojing.crawler.work.push;

import com.vmojing.crawler.queue.BasicQueue;

public interface PushStrategy {
	<T> void push(BasicQueue<T> queue,T obj);
}
