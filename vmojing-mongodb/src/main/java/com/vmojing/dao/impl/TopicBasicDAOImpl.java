package com.vmojing.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmojing.dao.TopicBasicDAO;
import com.vmojing.domain.TopicBasic;
import com.vmojing.domain.convert.TopicBasicConverter;


public class TopicBasicDAOImpl extends BasicDAO implements TopicBasicDAO{
	/** 默认表单名 */
	private static final String DEFAULT_COLLECTION_NAME = "topic_basic";
	private final TopicBasicConverter topicBasicConverter = new TopicBasicConverter();
	public TopicBasicDAOImpl(){
		super(DEFAULT_COLLECTION_NAME);
	}
	public TopicBasicDAOImpl(String collectionName){
		super(collectionName);
	}
	@Override
	public Map<ObjectId, String> getTopicNameIdMap() {
		// TODO Auto-generated method stub
		Map<ObjectId, String> topicIdNameMap = new HashMap<ObjectId, String>();
		DBCursor cursor = findByAll();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				TopicBasic tb = topicBasicConverter.convert(obj);
				topicIdNameMap.put(tb.getId(), tb.getTopicName());
			}
		} catch (Exception e) {
			// TODO: handle exception
			getLogger().error(e.toString());
		} finally {
			if(cursor != null)cursor.close();
		}
		return topicIdNameMap;
	}
	
}
