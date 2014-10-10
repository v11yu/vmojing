package com.vmojing.domain.convert;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.vmojing.domain.TopicBasic;
import com.vmojing.domain.constant.TopicBasicConstant;

public class TopicBasicConverter extends Converter<TopicBasic>{

	@Override
	public TopicBasic convert(DBObject obj) {
		// TODO Auto-generated method stub
		TopicBasic t = new TopicBasic();
		t.setCreateAtTime((Date) obj.get(TopicBasicConstant.CREATE_AT_TIME_FIELD));
		t.setId((ObjectId) obj.get(TopicBasicConstant.ID_FIELD));
		t.setKeyWords((BasicDBList) obj.get(TopicBasicConstant.KEY_WORDS_FIELD));
		t.setOpStatus((Integer) obj.get(TopicBasicConstant.OP_STATUS_FIELD));
		t.setSumCount((Integer) obj.get(TopicBasicConstant.SUM_COUNT_FIELD));
		t.setTodayCount((Integer) obj.get(TopicBasicConstant.TODAY_COUNT_FIELD));
		t.setTopicName((String) obj.get(TopicBasicConstant.TOPIC_NAME_FIELD));
		t.setTopicStatus((Integer) obj.get(TopicBasicConstant.TOPIC_STATUS_FIELD));
		t.setType((Integer) obj.get(TopicBasicConstant.TYPE_FILED));
		t.setUpdateFrequency((Integer) obj.get(TopicBasicConstant.UPDATE_FREQUENCY_FILED));
		t.setUpdateTime((Date) obj.get(TopicBasicConstant.UPDATE_TIME_FILED));
		return t;
	}

	@Override
	protected DBObject convertToDBObject(TopicBasic from) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		obj.put(TopicBasicConstant.ID_FIELD, from.getId());
		obj.put(TopicBasicConstant.CREATE_AT_TIME_FIELD, from.getCreateAtTime());
		obj.put(TopicBasicConstant.KEY_WORDS_FIELD, from.getKeyWords());
		obj.put(TopicBasicConstant.OP_STATUS_FIELD, from.getOpStatus());
		obj.put(TopicBasicConstant.SUM_COUNT_FIELD, from.getSumCount());
		obj.put(TopicBasicConstant.TOPIC_STATUS_FIELD, from.getTopicStatus());
		obj.put(TopicBasicConstant.TODAY_COUNT_FIELD, from.getTodayCount());
		obj.put(TopicBasicConstant.TOPIC_NAME_FIELD, from.getTopicName());
		obj.put(TopicBasicConstant.TYPE_FILED, from.getType());
		obj.put(TopicBasicConstant.UPDATE_FREQUENCY_FILED, from.getUpdateFrequency());
		obj.put(TopicBasicConstant.UPDATE_TIME_FILED, from.getUpdateTime());
		return obj;
	}

}
