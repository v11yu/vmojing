package com.vmojing.crawler.sina;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.mongodb.business.AccessTokenAllocation;

import weibo4j.Friendships;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class UsersTest {
	private static final Logger log = LoggerFactory.getLogger(UsersTest.class);
	Users us;
	Friendships fs;
	@Before
	public void initialize(){
		us = new Users();
		us.setToken(AccessTokenAllocation.getAccessToken());
		fs = new Friendships();
		fs.setToken(AccessTokenAllocation.getAccessToken());
	}
	@Test
	public void testGetUser() {
		User u;
		try {
			u = us.showUserByName("暴走漫画");
			System.out.println(u);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			System.out.println(""+e);
		}
		
	}
	@Test
	public void testGetFans() throws WeiboException{
		Map<String, Integer> mp = new HashMap<String, Integer>();
		UserWapper uw = null;
		String uname = "v11yu";
		String uid = "1744649233";
		for(int i=0;i<100;i++){

				uw =fs.getFollowersById(uid, 200,i*200 );

			System.out.println(i+" uw"+uw.getUsers().size());
			System.out.println(uw.getNextCursor());
			int preSize = mp.size();
			for(User u : uw.getUsers()){
				log.info(uname +" "+u);
				mp.put(u.getId(), 1);
			}
			if(preSize == mp.size()){
				System.out.println(i+" no create and exit ,map size:"+mp.size());
				break;
			}
		}
		
		
	}
}
