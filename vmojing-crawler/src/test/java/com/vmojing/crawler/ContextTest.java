package com.vmojing.crawler;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBConvertor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerRootConfiguration.class})
public class ContextTest {
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Autowired
	ApplicationContext context;
	@Autowired
	@Qualifier("crawlerProperties")
	Properties env;
	@Test
	public void testContext(){
		System.out.println(topicDao.countAndClose(topicDao.findByAll()));
		assertNotNull(topicDao);
	}
	@Test
	public void testEnv(){
		System.out.println(env.getProperty("dbName"));
	}
}
