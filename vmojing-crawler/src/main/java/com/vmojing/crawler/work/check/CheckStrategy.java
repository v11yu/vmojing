package com.vmojing.crawler.work.check;

public interface CheckStrategy {
	<T> boolean check(T t);
}
