package com.vmojing.core.parser.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import weibo4j.Oauths;
import weibo4j.Tags;
import weibo4j.model.Tag;
import weibo4j.model.TagWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;

import com.vmojing.core.parser.WeiboExceptionHandle;
import com.vmojing.core.parser.api.TagParser;
import com.vmojing.core.parser.convert.CommentConverter;
import com.vmojing.core.parser.convert.TagConverter;
import com.vmojing.mongodb.business.AccessTokenAllocation;
@Component
@Scope("prototype")
public class TagParserImpl implements TagParser{
	private static Logger log = LoggerFactory.getLogger(TagParserImpl.class);
	@Autowired
	private TagConverter tagConverter;
	Oauths os;
	Tags ts;
	String tagId;
	String uid;
	@PostConstruct
	public void initialize(){
		ts = new Tags();
		String token = AccessTokenAllocation.getAccessToken();
		log.info(token);
		ts.setToken(token);
	}
	@Override
	public List<com.vmojing.mongodb.domain.Tag> getTagsFromUsers(String uids) {
		// TODO Auto-generated method stub
		List<com.vmojing.mongodb.domain.Tag> res = new ArrayList<com.vmojing.mongodb.domain.Tag>();
		try {
			TagWapper tw = ts.getTagsBatch(uids);
			for(Tag t : tw.getTags()){
				res.add(tagConverter.convert(t));
			}
			return res;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			log.error("出错用户名串:"+uids);
			WeiboExceptionHandle.getErrorString(e);
		}catch (Exception e) {
			// TODO: handle exception
			log.error("出错用户名串:"+uids);
		}
		return null;
	}
	@Override
	public com.vmojing.mongodb.domain.Tag getTag(String tagName) {
		init(tagName);
		Tag res = findTag();
		after();
		// TODO Auto-generated method stub
		return tagConverter.convert(res);
	}
	public Tag findTag(){
		try {
			List<Tag> ls = ts.getTags(uid);
			for(Tag t:ls){
				if(t.getId().equals(tagId))
					return t;
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			log.error(WeiboExceptionHandle.getErrorString(e));
		}
		return null;
	}
	public void init(String tagName){
		os = new Oauths();
		os.setToken(AccessTokenAllocation.getAccessToken());

		createTag(tagName);
		getUid();
	}
	public void after(){
		dropTag();
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
	public void createTag(String tagName){
		try {
			JSONArray ls = ts.createTags(tagName);
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
