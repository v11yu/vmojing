package com.vmojing.crawler.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.crawler.client.CrawlerClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerRootConfiguration.class})
public class CrawlerClientTest {
	@Autowired
	CrawlerClient cclient;
	@Test
	public void testConfig(){
		System.out.println(cclient.maxBlogger);
	}
}
