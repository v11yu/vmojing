package com.vmojing.crawler.fetcher;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.crawler.CrawlerRootConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerRootConfiguration.class})
public class MobileTopicFetcherTest {
	@Autowired
	@Qualifier("mobileTopicFetcher")
	TopicFetcher topicFetcher;
	@Test
	public void testInit(){
		assertNotNull(topicFetcher);
	}
	@Test
	public void testGetIds(){
		Set<String> wids = topicFetcher.getIds("魔兽世界",2,"","");
		assertTrue(wids.size()>0);
	}
}
