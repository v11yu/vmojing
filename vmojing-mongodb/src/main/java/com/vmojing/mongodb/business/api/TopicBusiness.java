package com.vmojing.mongodb.business.api;

import java.util.List;

import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;

public interface TopicBusiness {
	/**
	 * save the topic to mongodb,or update if this object has already exist
	 * @param t
	 * @return
	 */
	boolean save(Topic t);
	/**
	 * save pair of weibo and topic
	 * @param t
	 * @param weibo
	 * @return
	 */
	boolean saveWeibo(Topic t,Weibo weibo);
	List<Topic> getAll();
}
