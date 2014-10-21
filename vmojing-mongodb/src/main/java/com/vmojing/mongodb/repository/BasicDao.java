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
public class BasicDao<T> {
	public void show(T t){
		System.out.println(t.getClass());
	}
}
