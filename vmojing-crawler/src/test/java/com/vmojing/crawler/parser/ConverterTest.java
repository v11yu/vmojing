package com.vmojing.crawler.parser;
import static org.junit.Assert.*;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.vmojing.crawler.parser.convert.CommentConverter;
import com.vmojing.crawler.parser.convert.Converter;
import com.vmojing.crawler.parser.convert.UserConverter;
import com.vmojing.crawler.parser.convert.WeiboConverter;
import com.vmojing.mongodb.business.AccessTokenAllocation;
import com.vmojing.mongodb.domain.Weibo;

public class ConverterTest {
	Converter<Weibo, Status> weiboConverter = new WeiboConverter();
	UserConverter userConverter = new UserConverter();
	CommentConverter commentConverter = new CommentConverter();
	@Test
	public void testGetSuperClass() throws InstantiationException,
			IllegalAccessException {
		Converter<Weibo, Status> c = new WeiboConverter();
		ParameterizedType pt = (ParameterizedType) c.getClass()
				.getGenericSuperclass();
		System.out.println(pt);
		Type[] t = pt.getActualTypeArguments();
		Weibo w = (Weibo) ((Class) t[0]).newInstance();
		System.out.println(w);
		assertNotNull(w);
	}
	@Test
	public void testSetAuto() throws SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(Method m : weiboConverter.getClass().getSuperclass().getDeclaredMethods()){
			System.out.println(m.getName());
			if(m.getName().equals("setAuto")){
				m.setAccessible(true);
				m.invoke(weiboConverter, new Status(), new Weibo());
			}
		}
	}
	@Test
	public void testWeiboConverter(){
		Timeline tm = new Timeline();
		tm.setToken(AccessTokenAllocation.getAccessToken());
		StatusWapper sw = null;
		Status st = null;
		try {
			sw = tm.getPublicTimeline();
			assertNotNull(sw);
			st = sw.getStatuses().get(0);
			assertNotNull(st);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Weibo weibo = weiboConverter.convert(st);
		System.out.println(weibo);
		assertEquals(weibo.getId(),st.getId());
	}
	@Test
	public void testuserConverter(){
		Users us = new Users();
		us.setToken(AccessTokenAllocation.getAccessToken());
		User u = null;
		try {
			u = us.showUserById("1744649233");
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.vmojing.mongodb.domain.User uu = userConverter.convert(u);
		System.out.println(u);
		System.out.println(uu);
		assertEquals(u.getId(), uu.getId());
	}
	@Test
	public void testCommentConverter(){
		Comments cs = new Comments();
		cs.setToken(AccessTokenAllocation.getAccessToken());
		CommentWapper cw = null;
		try {
			cw = cs.getCommentById("3772418675603324");
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			System.out.println(""+e);
		}
		assertNotNull(cw);
		List<Comment> cls = cw.getComments();
		Comment cc = cls.get(0);
		System.out.println("cls："+cls.size());
		assertTrue(cls.size()>0);
		com.vmojing.mongodb.domain.Comment c = commentConverter.convert(cls.get(0));
		System.out.println(c);
		assertEquals((long)c.getId(),cc.getId());
		//assertEquals(c.getId(),);
		
	}
	
}
