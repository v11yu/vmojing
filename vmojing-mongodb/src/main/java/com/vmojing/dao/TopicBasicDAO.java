package com.vmojing.dao;

import java.util.HashMap;

import org.bson.types.ObjectId;

public interface TopicBasicDAO {
	/**
	 * 获取 话题name和Id的hashMap
	 * @return hashMap<话题Id,话题Name>
	 */
	public HashMap<ObjectId, String> getTopicNameIdMap();
}
