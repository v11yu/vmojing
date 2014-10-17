package com.vmojing.crawler.crawler;

public abstract class AbstractCrawler<T> {
	public AbstractCrawler(){
		initialize();
	}
	protected abstract void initialize();
	protected abstract void work();
	protected abstract void saveAndUpdate(T obj);
}
