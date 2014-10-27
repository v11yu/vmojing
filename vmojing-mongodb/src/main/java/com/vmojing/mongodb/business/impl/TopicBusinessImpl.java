package com.vmojing.mongodb.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepository;
@Component
public class TopicBusinessImpl implements TopicBusiness{
	private final static Logger log = LoggerFactory.getLogger(TopicBusinessImpl.class);
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Override
	public boolean save(Topic t) {
		// TODO Auto-generated method stub
		try {
			topicDao.saveAndUpdate(t);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
		return false;
	}

}
