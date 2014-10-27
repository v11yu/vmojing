package com.vmojing.mongodb.repository;

import static org.junit.Assert.*;

import java.util.Date;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DBObject;
import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.domain.Topic;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRootConfiguration.class})
public class DBConverterTest {
	@Autowired
	@Qualifier("topicConvertor")
	private DBConvertor<Topic> topicConvertor;
	@Test
	public void testToDBObject(){
		Topic t = new Topic(new Date(),new
				 Date(),10,100,"hi",1,new Date());
		DBObject obj = topicConvertor.convertToDB(t);
		Topic t2 = topicConvertor.convertToPojo(obj);
		assertEquals(t.toString(),t2.toString());
	}
	@Test
	public void testError(){
		Topic t = new Topic();
		t.setCreateAtTime(new Date());
		DBObject obj = topicConvertor.convertToDB(t);
		obj.put("extra", "extraValue");
		Topic tt = topicConvertor.convertToPojo(obj);
		System.out.println(tt.toString());
	}

}
