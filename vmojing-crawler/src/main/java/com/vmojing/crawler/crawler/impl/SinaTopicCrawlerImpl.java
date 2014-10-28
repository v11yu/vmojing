package com.vmojing.crawler.crawler.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.crawler.Command;
import com.vmojing.crawler.crawler.SinaTopicCrawler;
import com.vmojing.crawler.fetcher.Loginer;
import com.vmojing.crawler.fetcher.TopicFetcher;
import com.vmojing.crawler.fetcher.mobile.MobileSinaLoginer;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;
@Component
public class SinaTopicCrawlerImpl implements Runnable,SinaTopicCrawler,Command{
	/**失败后的重试次数 */
	private static final Integer TRY_TIME = 3;
	/**单次抓取的页数 */
	private static final Integer PAGE_NUM = 5;
	/** 日志 */
	private static final Logger log = LoggerFactory.getLogger(SinaTopicCrawlerImpl.class);
	
	private List<Topic> topics;
	@Autowired
	private TopicFetcher topicFetcher;
	public SinaTopicCrawlerImpl() {
		// TODO Auto-generated constructor stub
		topics = new ArrayList<Topic>();
	}
	@Override
	public void addTopic(Topic t) {
		// TODO Auto-generated method stub
		topics.add(t);
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
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String names = "";
		for(Topic t:topics){
			names+=t.getTopicName();
		}
		log.info("crawler topics<"+names+"> begin!");
		Set<String> wids = topicFetcher.getIds(names, this.PAGE_NUM, "", "");
		List<Weibo> weibos = getWeibos(wids);
	}
	

	
}
