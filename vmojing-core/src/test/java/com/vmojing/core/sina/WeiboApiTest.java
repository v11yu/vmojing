package com.vmojing.core.sina;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Timeline;


import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import com.vmojing.mongodb.business.AccessTokenAllocation;

public class WeiboApiTest {
	private static Logger log = LoggerFactory.getLogger(WeiboApiTest.class);
	Timeline tm ;
	@Before
	public void init(){
		tm = new Timeline();
		String token = "2.00P5wdvD3mz1gE1fe7e743fazQm7IE";
		System.out.println(token);
		tm.setToken(token);
	}
	@Test
	public void testGetWeibo() throws WeiboException{
		String wid = "3775347927519624";
		
		StatusWapper sw = tm.getStatusByIds(wid, 0);
		log.info(""+sw.getStatuses().get(0));
	}

}
