package com.vmojing.crawler.crawler.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.crawler.crawler.SinaTopicCrawler;
import com.vmojing.crawler.fetcher.Loginer;
import com.vmojing.crawler.fetcher.TopicFetcher;
import com.vmojing.crawler.fetcher.mobile.MobileSinaLoginer;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;

public class SinaTopicCrawlerImpl implements Runnable,SinaTopicCrawler{
	/**失败后的重试次数 */
	private static final Integer TRY_TIME = 3;
	/**单次抓取的页数 */
	private static final Integer PAGE_NUM = 5;
	/** 日志 */
	private static final Logger log = LoggerFactory.getLogger(SinaTopicCrawlerImpl.class);
	@Override
	public void addTopic() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Weibo> getWeibos(Set<String> wids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<String> getWids(Topic t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
		    //doing sth
			//休眠一断时间,中断时会抛出InterruptedException  
            Thread.sleep(50);
		}catch(InterruptedException e){
		//doing ending
		}finally{
		//free memory...
		}
	}
	

	
}
