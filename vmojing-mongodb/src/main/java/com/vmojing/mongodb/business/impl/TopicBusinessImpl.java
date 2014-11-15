package com.vmojing.mongodb.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmojing.mongodb.business.AbstractBusiness;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBConvertor;
@Component
public class TopicBusinessImpl extends AbstractBusiness implements TopicBusiness {
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Autowired
	@Qualifier("topicConvertor")
	DBConvertor<Topic> topicConvertor;
	@Override
	public boolean save(Topic t) {
		// TODO Auto-generated method stub
		try {
			topicDao.saveAndUpdate(t);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error("save topic fail "+e.toString());
		}
		return false;
	}
	@Override
	public List<Topic> getAll() {
		// TODO Auto-generated method stub
		DBCursor cursor = null;
		List<Topic> res = new ArrayList<Topic>();
		try{
			cursor = topicDao.findByAll();
			while(cursor.hasNext()){
				DBObject obj = cursor.next();
				res.add(topicConvertor.convertToPojo(obj));
			}
		}catch(Exception e){
			getLogger().error("topic getAll throw error " +e);
			res = null;
		}finally{
			if(cursor != null){
				cursor.close();
			}
		}
		return res;
	}
}
