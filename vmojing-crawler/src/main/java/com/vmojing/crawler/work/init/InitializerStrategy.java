package com.vmojing.crawler.work.init;

public interface InitializerStrategy<T> {
	void initialize(T t);
}
