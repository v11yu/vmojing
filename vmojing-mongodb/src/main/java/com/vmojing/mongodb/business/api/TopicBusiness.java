package com.vmojing.mongodb.business.api;

import java.util.List;

import com.vmojing.mongodb.domain.Topic;

public interface TopicBusiness {
	boolean save(Topic t);
	List<Topic> getAll();
}
