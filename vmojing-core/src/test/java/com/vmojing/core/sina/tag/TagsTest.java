package com.vmojing.core.sina.tag;

import org.junit.Before;
import org.junit.Test;

import com.vmojing.mongodb.business.AccessTokenAllocation;
import com.vmojing.mongodb.domain.AccessToken;

import weibo4j.Tags;
import weibo4j.model.TagWapper;
import weibo4j.model.WeiboException;

public class TagsTest {
	Tags ts;
	@Before
	public void init(){
		ts.setToken(AccessTokenAllocation.getAccessToken());
	}
	@Test
	public void testTags(){
		String uids = "1639498782,2146857211";
		
		TagWapper tw = null;
		try {
			tw = ts.getTagsBatch(uids);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
