package com.vmojing.crawler.parser;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.crawler.fetcher.TopicFetcher;
import com.vmojing.mongodb.domain.Weibo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerRootConfiguration.class})
public class WeiboParserTest {
	@Autowired
	WeiboParser weiboParser;
	@Autowired
	@Qualifier("mobileTopicFetcher")
	TopicFetcher topicFetcher;
	
	private Set<String> wids;
	private Date lastUpdateTime;
	@SuppressWarnings("deprecation")
	@Before
	public void init(){
		wids = topicFetcher.getIds("魔兽世界", 2, "", "");
		Date nowTime = new Date();
		lastUpdateTime = new Date(nowTime.getYear(), nowTime.getMonth(), nowTime.getDate());
	}
	@Test
	public void testInit(){
		System.out.println(lastUpdateTime);
		assertTrue(wids.size()>0);
	}
	@Test
	public void testGetWids(){
		List<Weibo> weibos = weiboParser.getWeibo(wids, lastUpdateTime);
	}
}
