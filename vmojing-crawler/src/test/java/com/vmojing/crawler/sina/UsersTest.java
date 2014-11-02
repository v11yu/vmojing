package com.vmojing.crawler.sina;

import org.junit.Test;

import com.vmojing.mongodb.business.AccessTokenAllocation;

import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class UsersTest {
	@Test
	public void testGetUser() throws WeiboException{
		Users us = new Users();
		us.setToken(AccessTokenAllocation.getAccessToken());
		User u = us.showUserByName("暴走漫画");
		System.out.println(u);
	}
}
