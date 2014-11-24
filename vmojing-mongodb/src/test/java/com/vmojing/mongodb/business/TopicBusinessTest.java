package com.vmojing.mongodb.business;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DBCursor;
import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRootConfiguration.class})
public class TopicBusinessTest {
	@Autowired
	TopicBusiness topicBusi;
	@Autowired
	@Qualifier("weiboDao")
	BasicRepository<Weibo> weiboDao;
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Before
	public void clear(){
		topicDao.dropCollection();
		Topic t = new Topic(new Date(),new
				 Date(),10,100,"hi",1,new Date());
		topicBusi.save(t);
	}
	@Test
	public void test(){
		assertEquals(1,topicDao.countAndClose(topicDao.findByAll()));
	}
	@Test
	public void testFindNotValue(){
		DBQuery query = new DBQuery();
		query.equalsOperation("topicName", "12345");
		DBCursor cursor = topicDao.findQuery(query);
		assertNotNull(cursor);
		System.out.println(cursor.size());
		assertEquals(cursor.size(), 0);
	}
	@Test
	public void testFindNotKeyName(){
		DBQuery query = new DBQuery();
		query.equalsOperation("notKeyName", "12345");
		DBCursor cursor = topicDao.findQuery(query);
		assertNotNull(cursor);
		Topic t = topicDao.findOne(cursor);
		assertEquals(t, null);
		System.out.println(cursor.size());
		assertEquals(cursor.size(), 0);
	}
	@Test
	public void testFindExistOne(){
		DBQuery query = new DBQuery();
		query.equalsOperation("topicName", "hi");
		DBCursor cursor = topicDao.findQuery(query);
		assertNotNull(cursor);
		System.out.println(cursor.size());
		assertEquals(cursor.size(), 1);
		Topic t = topicDao.findOne(cursor);
		System.out.println(t);
		assertEquals("hi",t.getTopicName());
	}
}
