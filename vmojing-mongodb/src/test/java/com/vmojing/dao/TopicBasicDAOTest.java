package com.vmojing.dao;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;









import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;



import org.junit.Rule;
import org.junit.Test;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.DBObject;
import com.vmojing.dao.impl.TopicBasicDAOImpl;
import com.vmojing.domain.TopicBasic;
import com.vmojing.domain.constant.TopicBasicConstant;
import com.vmojing.domain.convert.TopicBasicConverter;

public class TopicBasicDAOTest {
	private final TopicBasicConverter topicBasicConverter = new TopicBasicConverter();
	@Rule
	public MongoDbRule remoteMongoDbRule =  newMongoDbRule().defaultManagedMongoDb("test");
	@Test
	@UsingDataSet(locations="initialTopicBasicData.json", loadStrategy=LoadStrategyEnum.CLEAN_INSERT)
	@ShouldMatchDataSet(location="expectedTopicBasicData.json")
	public void testUpdate() {
		TopicBasicDAOImpl tdao = new TopicBasicDAOImpl();
		TopicBasic t = new TopicBasic();
		DBObject obj = tdao.findOne();
		t = topicBasicConverter.convert(obj);
		t.setSumCount(1774);
		t.setTodayCount(7);
		List<String> ls = new ArrayList<String>();
		ls.add(TopicBasicConstant.SUM_COUNT_FIELD);
		ls.add(TopicBasicConstant.TODAY_COUNT_FIELD);
		tdao.update(topicBasicConverter.toDBObject(t), ls);
	}
	@Test
	@UsingDataSet(locations="initialTopicBasicData.json", loadStrategy=LoadStrategyEnum.CLEAN_INSERT)
	public void testDomain(){
		TopicBasic topicBasic = new TopicBasic();
		topicBasic.setTodayCount(100);
		TopicBasicDAOImpl tdao = new TopicBasicDAOImpl();
		tdao.save(topicBasicConverter.toDBObject(topicBasic));
		assertEquals(tdao.findByAll().size(),2);
	}
}
