package com.vmojing.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vmojing.domain.TopicBasic;
import com.vmojing.domain.convert.TopicBasicConverter;

public class TopicBasicTest {
	private final TopicBasicConverter topicBasicConverter = new TopicBasicConverter();
	
	@Test
	public void testConverter(){
		TopicBasic topicBasic = new TopicBasic();
		topicBasic.setTodayCount(100);
		assertEquals(topicBasicConverter.toDBObject(topicBasic).toMap().size(),1);

	}
}
