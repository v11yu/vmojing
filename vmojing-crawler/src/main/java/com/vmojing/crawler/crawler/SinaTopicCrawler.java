package com.vmojing.crawler.crawler;

import java.util.List;
import java.util.Set;

import com.vmojing.crawler.fetcher.TopicFetcher;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;

public interface SinaTopicCrawler {
	void addTopic(Topic t);
	List<Weibo> getWeibos(Set<String> wids);
	Set<String> getWids(Topic t);
	
}
