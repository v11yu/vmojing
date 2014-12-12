package com.vmojing.mongodb.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.vmojing.mongodb.business.AbstractBusiness;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBConvertor;
import com.vmojing.mongodb.repository.DBQuery;
@Component
public class TopicBusinessImpl extends AbstractBusiness implements TopicBusiness {
	@Autowired
	@Qualifier("topicDao")
	BasicRepository<Topic> topicDao;
	@Autowired
	@Qualifier("topicConvertor")
	DBConvertor<Topic> topicConvertor;
	@Autowired
	@Qualifier("weiboDao")
	BasicRepository<Weibo> weiboDao;
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
	@Override
	public boolean saveWeibo(Topic t, Weibo weibo) {
		// TODO Auto-generated method stub
		Weibo w = weiboDao.findById(weibo.getId());
		if(w == null){
			// 之前没有该微博信息
			w = weibo;
		}
		List<ObjectId> topicIds = w.getTopicIds();
		if(topicIds == null){
			topicIds = new ArrayList<ObjectId>();
		}
		if(topicIds.contains(w.getId())){
			getLogger().error("已经存在微博："+weibo+"，在话题："+t.getTopicName()+",这是系统异常");
			return false;
		}
		topicIds.add(t.getId());
		try {
			weiboDao.saveAndUpdate(w);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error("save weibo&topic fail");
			return false;
		}
		return true;
	}
	@Override
	public Topic getById(String id) {
		// TODO Auto-generated method stub
		return topicDao.findById(new ObjectId(id));
	}
	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		WriteResult wr = topicDao.dropById(new ObjectId(id));
		getLogger().info("删除"+wr);
		return true;
	}
}
