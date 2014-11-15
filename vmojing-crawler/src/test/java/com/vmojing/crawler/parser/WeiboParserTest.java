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

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.crawler.fetcher.api.TopicFetcher;
import com.vmojing.crawler.fetcher.util.IdTransferUtil;
import com.vmojing.crawler.parser.api.WeiboParser;
import com.vmojing.crawler.parser.convert.Converter;
import com.vmojing.crawler.parser.convert.WeiboConverter;
import com.vmojing.crawler.parser.impl.WeiboParserImpl;
import com.vmojing.mongodb.business.AccessTokenAllocation;
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
		wids = topicFetcher.getIds("蕉城区", 2, "", "");
		Date nowTime = new Date();
		lastUpdateTime = new Date(nowTime.getYear(), nowTime.getMonth(), nowTime.getDate());
	}
	@Test
	public void testInit(){
		System.out.println(lastUpdateTime);
		assertTrue(wids.size()>0);
	}
	@Test
	public void testGetWeiboByWids(){
		List<Weibo> weibos = weiboParser.getWeiboByWids(wids, lastUpdateTime);
		System.out.println(weibos.size());
		assertTrue(weibos.size()>0);
		for(Weibo w : weibos){
			System.out.println(w.getCreatedAt());
			assertTrue(w.getCreatedAt().after(lastUpdateTime));
		}
	}
	@Test
	public void testGetWeiboByUid(){
		String uid = "2144684673";
		List<Weibo> weibos = weiboParser.getWeiboByUid(uid, lastUpdateTime);
		System.out.println(weibos.size());
		assertTrue(weibos.size()>0);
		for(Weibo w : weibos){
			System.out.println(w.getCreatedAt() +" "+ w);
			assertTrue(w.getCreatedAt().after(lastUpdateTime));
		}
	}
	@Test
	public void testGetRetweet(){
		String wid = "";
		String mid = "BufNlqiUL";
		wid = IdTransferUtil.mid2Id(mid);
		List<Weibo> weibos = weiboParser.getRetweet(wid, lastUpdateTime);
		System.out.println(weibos.size());
		assertTrue(weibos.size()>0);

	}


	
}
