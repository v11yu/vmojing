package com.vmojing.core.sina.tag;

import java.net.URLEncoder;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Oauths;
import weibo4j.Tags;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.Tag;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.util.URLEncodeUtils;

import com.vmojing.core.sina.WeiboApiTest;
import com.vmojing.core.util.IdTransferUtil;
import com.vmojing.mongodb.business.AccessTokenAllocation;

public class TagTest {
	private static Logger log = LoggerFactory.getLogger(WeiboApiTest.class);
	Oauths os;
	Tags ts;
	String tagId;
	String uid;
	@Before
	public void init(){
		os = new Oauths();
		os.setToken(AccessTokenAllocation.getAccessToken());
		ts = new Tags();
		ts.setToken(AccessTokenAllocation.getAccessToken());
		createTag();
		getUid();
	}
	@After
	public void after(){
		dropTag();
	}

	
	@Test
	public void testGetTag(){
		try {
			
			List<Tag> ls = ts.getTags(uid);
			for(Tag t:ls){
				if(t.getId().equals(tagId))
				System.out.println(t.getWeight());
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getUid(){
		try {
			uid = os.getUserInfo()+"";
			System.out.println("uid："+uid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void dropTag(){
		try {
			ts.destoryTag(Integer.parseInt(tagId));
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createTag(){
		try {
			JSONArray ls = ts.createTags("旅游");
			for(int i=0;i<ls.length();i++){
				try {
					weibo4j.org.json.JSONObject obj = ls.getJSONObject(i);
					System.out.println(obj);
					tagId = (String) obj.get("tagid");
					System.out.println("tagid:"+tagId);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(ls);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
