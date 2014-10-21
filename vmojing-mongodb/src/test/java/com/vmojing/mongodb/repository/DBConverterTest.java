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
import com.vmojing.mongodb.RootConfiguration;
import com.vmojing.mongodb.domain.Topic;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfiguration.class})
public class DBConverterTest {
	@Autowired
	@Qualifier("topicConvertor")
	private DBConvertor<Topic> topicConvertor;
	@Test
	public void testToDBObject(){
		Topic t = new Topic(new Date(),new
				 Date(),10,100,"hi",1);
		DBObject obj = topicConvertor.convertFrom(t);
		Topic t2 = topicConvertor.convertTo(obj);
		assertEquals(t.toString(),t2.toString());
	}

}
