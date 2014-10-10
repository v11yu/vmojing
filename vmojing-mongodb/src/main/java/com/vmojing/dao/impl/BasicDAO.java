package com.vmojing.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.vmojing.dao.DAO;
import com.vmojing.dao.MongoDbUtil;


public class BasicDAO implements DAO{
	/** 日志容器*/
	@SuppressWarnings("rawtypes")
	private static final Map<Class,Logger> loggers = new HashMap<Class,Logger>();
	protected DBCollection Collection;
	public BasicDAO(String collectionName){
		this.Collection = MongoDbUtil.getCollection(collectionName);
	}
	/**
	 * 获取当前日志
	 * @return 当前类日志
	 */
	protected Logger getLogger()
	{
	    Logger logger = null;
	    if (BasicDAO.loggers.containsKey(this.getClass())) {
	        logger = BasicDAO.loggers.get(this.getClass());
	    } else {
	        logger = LoggerFactory.getLogger(this.getClass());
	        BasicDAO.loggers.put (this.getClass(), logger);
	    }
	    return logger;
	}
	@Override
	public DBCursor findByAll() {
		DBCursor cursor = Collection.find();
		return cursor;
	}

	@Override
	public DBCursor findByValueLessThanEquals(String valueName, Object value) {
		DBObject query = QueryBuilder.start().put(valueName).lessThanEquals(value).get();
		DBCursor cursor = Collection.find(query);
		return cursor;
	}

	@Override
	public DBCursor findByValueGreaterThanEquals(String valueName, Object value) {
		DBObject query = QueryBuilder.start().put(valueName).greaterThanEquals(value).get();
		DBCursor cursor = Collection.find(query);
		return cursor;
	}

	@Override
	public DBCursor findByValueEquals(String valueName, Object value) {
		DBObject query = QueryBuilder.start().and(valueName).is(value).get();
		DBCursor cursor = Collection.find(query);
		return cursor;
	}

	@Override
	public void save(DBObject obj) {
		getLogger().debug(obj.toString());
		Collection.save(obj);
		
	}

	@Override
	public void saveAll(DBCursor cursor) {
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			save(obj);
		}
	}

	@Override
	public void dropAll() {
		Collection.drop();
		
	}

	@Override
	public void update(DBObject obj, List<String> keyNames) {
		DBObject query = new BasicDBObject("_id",obj.get("_id"));
		BasicDBObject updateObj = new BasicDBObject();
        for(String keyName : keyNames){
        	updateObj.append( keyName,obj.get(keyName));
        }
        Collection.update(query, new BasicDBObject("$set", updateObj));
		
	}

	@Override
	public void update(DBObject obj, String keyName) {
		DBObject query = new BasicDBObject("_id",obj.get("_id"));
        DBObject updateObj = new BasicDBObject(keyName,obj.get(keyName));
        Collection.update(query, new BasicDBObject("$set", updateObj));
		
	}

	@Override
	public DBCursor sort(String keyName, int op, DBCursor cursor) {
		return cursor.sort(new BasicDBObject(keyName,op));
	}

	@Override
	public DBCursor findByPage(Integer itemCount, Integer pageNum,
			DBCursor cursor) {
		return cursor.skip(itemCount * pageNum).limit(itemCount);
	}
	@Override
	public DBObject findOne(){
		return Collection.findOne();
	}
	
}
