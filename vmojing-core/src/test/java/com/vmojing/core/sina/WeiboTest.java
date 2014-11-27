package com.vmojing.core.sina;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Timeline;


import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

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
	public void testGetWeibo() throws WeiboException{
		String wid = "3775347927519624";
		StatusWapper sw = tm.getStatusByIds(wid, 0);
		log.info(""+sw.getStatuses().get(0));
	}
}
