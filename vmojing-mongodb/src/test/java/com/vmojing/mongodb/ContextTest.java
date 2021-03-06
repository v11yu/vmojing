package com.vmojing.mongodb;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoRootConfiguration.class})
public class ContextTest {
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Test
	public void testContext(){
		System.out.println(topicDao.countAndClose(topicDao.findByAll()));
		assertNotNull(topicDao);
	}
	
}
