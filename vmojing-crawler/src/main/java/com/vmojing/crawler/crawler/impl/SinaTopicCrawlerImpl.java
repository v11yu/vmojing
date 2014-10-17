package com.vmojing.crawler.crawler.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.crawler.fetcher.Loginer;
import com.vmojing.crawler.fetcher.TopicFetcher;
import com.vmojing.crawler.fetcher.mobile.MobileSinaLoginer;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;

public class SinaTopicCrawlerImpl implements Runnable{
	/**失败后的重试次数 */
	private static final Integer TRY_TIME = 3;
	/**单次抓取的页数 */
	private static final Integer PAGE_NUM = 5;
	/** 日志 */
	private static final Logger log = LoggerFactory.getLogger(SinaTopicCrawlerImpl.class);
	
	
	private String topicNames;
	private TopicFetcher topicFetcher;
	/**
	 * @param topicNames 需要爬取的话题Name，用半角逗号分隔
	 */
	public SinaTopicCrawlerImpl(String topicNames){
		this.topicNames = topicNames;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		initialize();
		if(topicFetcher == null) return ;
		Set<String> wids = topicFetcher.getIds(topicNames, PAGE_NUM, "", "");
	}
	/**
	 * 初始化TopicFetcher
	 */
	private void initialize(){
		Loginer loginer = new MobileSinaLoginer();
		int i=0;
		for(i=0;!loginer.login()&&i<TRY_TIME;i++){
			log.warn("httpClient登陆失败，正在尝试，尝试次数："+i);
		}
		if(i == TRY_TIME){
			log.error("httpClient登陆失败，本次话题<"+topicNames+">抓取异常结束！");
			return ;
		}
		topicFetcher = new MobileTopicFetcher(loginer.getClient());
	}
	
}
