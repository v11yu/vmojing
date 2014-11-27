package com.vmojing.crawler.sina;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Timeline;

import com.vmojing.mongodb.business.AccessTokenAllocation;

public class WeiboTest {
	private static Logger log = LoggerFactory.getLogger(WeiboTest.class);
	Timeline tm ;
	@Before
	public void init(){
		tm = new Timeline();
		tm.setToken(AccessTokenAllocation.getAccessToken());
	}
	@Test
	public void testGetWeibo(){
		
	}
}
