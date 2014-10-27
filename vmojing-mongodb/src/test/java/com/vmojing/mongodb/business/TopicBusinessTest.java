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

import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRootConfiguration.class})
public class TopicBusinessTest {
	@Autowired
	TopicBusiness topicBusi;
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Before
	public void clear(){
		topicDao.dropCollection();
	}
	@Test
	public void test(){
		Topic t = new Topic(new Date(),new
				 Date(),10,100,"hi",1,new Date());
		topicBusi.save(t);
		assertEquals(1,topicDao.countAndClose(topicDao.findByAll()));
	}
}
