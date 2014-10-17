package com.vmojing.mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmojing.dao.impl.BasicDAO;
import com.vmojing.mongodb.domain.Topic;

@Repository
public class BasicDao {
	@Autowired
    MongoTemplate mongoTemplate;
	BasicDAO aa = new BasicDAO("topic");
	public void show(){
		DBCursor cursor = aa.findByAll();
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			Topic topic = mongoTemplate.getConverter().read(Topic.class, obj);
			System.out.println(topic.toString());
			//System.out.println(obj.toString());
		}
		cursor.close();
	}
}
