package com.vmojing.mongodb.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.DBObject;
import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.utils.SpringConfigSingleton;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRootConfiguration.class})
public class BasicRepositoryTest {
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultManagedMongoDb("test");
	@Test
	public void testCreateCollection(){
		topicDao.createCollection();
	}
	@Test
	public void testSava() throws Exception{
		topicDao.dropAll();
		Topic t = new Topic(new Date(),new
				 Date(),10,100,"hi",1,new Date());
		topicDao.saveAndUpdate(t);
		assertEquals(topicDao.countAndClose(topicDao.findByAll()), 1);
		
	}
	@Test
	@UsingDataSet(locations = "initialTopicData.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	@ShouldMatchDataSet(location="expectedTopicData.json")
	public void testUpdate(){
		Topic t = topicDao.findOne();
		t.setTopicName("hello");
		t.setType(10);
		topicDao.update(t, "topicName,type");
		Topic g = topicDao.findOne();
		assertEquals(t.toString(), g.toString());
		
	}
	@Test
	@UsingDataSet(locations = "initialTopicData.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void testSaveAndUpdate() throws Exception{
		Topic t = topicDao.findOne();
		t.setTopicName("hello");
		t.setType(null);
		topicDao.saveAndUpdate(t);
	}
	@Test
	public void testSaveNew() throws Exception{
		topicDao.dropAll();
		Topic t = new Topic(new Date(),new
				 Date(),10,100,"hi",1,new Date());
		topicDao.saveAndUpdate(t);
		t = new Topic(new Date(),new
				 Date(),10,100,"hi",1,new Date()); 
		topicDao.saveAndUpdate(t);
		t.setType(5);
		topicDao.saveAndUpdate(t);
		assertEquals(topicDao.countAndClose(topicDao.findByAll()), 2);
	}
	@Test
	@UsingDataSet(locations = "multipleTopicData.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void testFindByEqualsOperation(){
		System.out.println(topicDao.countAndClose(topicDao.findByAll()));
		DBQuery query = new DBQuery();
		query.equalsOperation("type", 20);
		assertEquals(topicDao.countAndClose(topicDao.findQuery(query)), 2);

	}
	@Test
	@UsingDataSet(locations = "multipleTopicData.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void testFindByGreaterThan(){
		System.out.println("sum:"+topicDao.countAndClose(topicDao.findByAll()));
		DBQuery query = new DBQuery();
		query.greaterThan("type", 20);
		query.greaterThan("operateStatus", 1);
		System.out.println("num:"+topicDao.countAndClose(topicDao.findQuery(query)));
		assertEquals(topicDao.countAndClose(topicDao.findQuery(query)), 1);

	}
}
