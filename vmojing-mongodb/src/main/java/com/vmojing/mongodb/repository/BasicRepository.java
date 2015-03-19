	package com.vmojing.mongodb.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.core.DAO;
import com.vmojing.mongodb.utils.MongoDbUtil;



public class BasicRepository<T> implements DAO<T>{
	/** 日志容器*/
	@SuppressWarnings("rawtypes")
	private static final Map<Class,Logger> loggers = new HashMap<Class,Logger>();
	/** 注册的document类 */
	private final Class<?> entityType;
	@Autowired
    private MongoTemplate mongoTemplate;
	protected final DBConvertor<T> convertor;
	protected DBCollection collection;
	/**
	 * 构造函数<p/>Example:
	 * <pre>
	 * new BasicRepository(Topic.class)
	 * </pre>
	 * @param entityType 对应的document类
	 * 
	 */
	
	public BasicRepository(Class<?> entityType,DBConvertor<T> convertor){
		getLogger().debug("create a Object of BasicRepository,type of "+entityType);
		this.entityType = entityType;
		this.convertor = convertor;
	}
	@PostConstruct
	private void init(){
		collection = MongoDbUtil.getCollection(getCollectionName());
	}
	/**
	 * 获取当前类的日志，用于继承情况：子类或父类<p/>
	 * get Logger for current class for subclass or superclass
	 * @return 
	 */
	protected Logger getLogger()
	{
	    Logger logger = null;
	    if (BasicRepository.loggers.containsKey(this.getClass())) {
	        logger = BasicRepository.loggers.get(this.getClass());
	    } else {
	        logger = LoggerFactory.getLogger(this.getClass());
	        BasicRepository.loggers.put (this.getClass(), logger);
	    }
	    return logger;
	}
	/**
	 * The collection name used for the specified class.
	 * @return
	 */
	protected String getCollectionName(){
		return mongoTemplate.getCollectionName(entityType);

	}
	/**
	 * Create an uncapped collection with a name based on the provided entity class.
	 * @param t
	 */
	public void createCollection() {
		getLogger().debug("operation of creating "+entityType);
        if (!mongoTemplate.collectionExists(entityType)) {
        	getLogger().debug("create collection with a name based on "+entityType);
            mongoTemplate.createCollection(entityType);
        }
    }
	/**
	 * Drop the collection with the name indicated by the entity class. 
	 */
	public void dropCollection(){
		getLogger().debug("operation of dropping "+entityType);
		if (mongoTemplate.collectionExists(entityType)) {
			getLogger().debug("drop collection with a name based on "+entityType);
            mongoTemplate.dropCollection(entityType);
        }
	}
	@Override
	public DBCursor findByAll() {
		// TODO Auto-generated method stub
		DBCursor cursor = collection.find();
		getLogger().debug("findByAll from "+getCollectionName()+" count:"+cursor.count());
		return collection.find();
	}
	@Override
	public DBCursor findQuery(DBObject query) {
		// TODO Auto-generated method stub
		getLogger().debug("findQuery :"+query+" from "+getCollectionName());
		return collection.find(query);
	}
	@Override
	public DBCursor findQuery(DBQuery query) {
		// TODO Auto-generated method stub
		return findQuery(query.getQuery());
	}
	@Override
	public DBCursor findQuery(QueryBuilder query) {
		// TODO Auto-generated method stub
		return findQuery(query.get());
	}

	@Override
	public void dropAll() {
		// TODO Auto-generated method stub
		this.dropCollection();
	}
	@Override
	public void update(DBObject obj, String keyNames) {
		// TODO Auto-generated method stub
		DBObject query = new BasicDBObject("_id",obj.get("_id"));
		//mongoTemplate.up
		BasicDBObject updateObj = new BasicDBObject();
		String names[] = keyNames.split(",");
        for(String keyName : names){
        	updateObj.append( keyName,obj.get(keyName));
        }
        collection.update(query, new BasicDBObject("$set", updateObj));
		
	}
	@Override
	public int countAndClose(DBCursor cursor) {
		// TODO Auto-generated method stub
		int res = -1;
		try{
			res = cursor.size();
		}catch(Exception e){
			getLogger().error("获取所有数据出错"+e.toString());
			getLogger().error(ExceptionUtils.getStackTrace(e));
		}finally{
			if(cursor != null) cursor.close();
		}
		return res;
	}
	@Override
	public T findOne() {
		// TODO Auto-generated method stub
		DBObject obj = collection.findOne();
		return convertor.convertToPojo(obj);
	}
	@Override
	public void update(T obj, String keyNames) {
		// TODO Auto-generated method stub
		update(convertor.convertToDB(obj),keyNames);
	}
	@Override
	public void saveAndUpdate(T obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			getLogger().debug(
					"saveAndUpdate :" + obj + " in " + getCollectionName());
			mongoTemplate.save(obj);
		} catch (Exception e) {
			getLogger().error("错误:save failure");
			getLogger().error(ExceptionUtils.getStackTrace(e));
			throw new Exception("save failure");
		} finally {

		}
	}
	@Override
	public void saveAndUpdate(DBObject obj) {
		// TODO Auto-generated method stub
		mongoTemplate.save(convertor.convertToPojo(obj));
	}
	@Override
	public T findOne(DBCursor cursor) {
		// TODO Auto-generated method stub
		if(cursor == null) return null;
		T t = null;
		try{
			while(cursor.hasNext()){
				DBObject obj = cursor.next();
				t = convertor.convertToPojo(obj);
				break;
			}
		}catch(Exception e){
			getLogger().error("错误:find one by cursor throw error:"+cursor);
			getLogger().error(ExceptionUtils.getStackTrace(e));
		}finally{
			if(cursor != null) cursor.close();
		}
		return t;
	}
	@Override
	public T findById(Object id) {
		// TODO Auto-generated method stub
		DBQuery query  = new DBQuery();
		query.equalsOperation("_id", id);
		T t = findOne(findQuery(query));
		return t;
	}
	@Override
	public WriteResult dropById(Object id) {
		// TODO Auto-generated method stub
		BasicDBObject document = new BasicDBObject();
		document.put("_id",id);
		return collection.remove(document);

	}
	@Override
	public List<T> findByPageNum(int skip, int limit) {
		// TODO Auto-generated method stub
		DBCursor cursor = findByAll();
		List<DBObject> ls = null;
		try{
			ls = cursor.skip(skip).limit(limit).toArray();
		}catch(Exception e){
			getLogger().error(ExceptionUtils.getStackTrace(e));
		}finally{
			if(cursor != null) cursor.close();
		}
		return dbobj2T(ls);
	}

	private List<T> dbobj2T(List<DBObject> ls) {
		try {
			List<T> res = new ArrayList<T>();
			for (DBObject obj : ls) {
				res.add(convertor.convertToPojo(obj));
			}
			return res;
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public List<T> dbobj2Entity(DBCursor cursor) {
		// TODO Auto-generated method stub
		List<DBObject> ls= cursor.toArray();
		return dbobj2T(ls);
	}
	@Override
	public void saveAll(List<T> all) {
		// TODO Auto-generated method stub
		for(T t:all){
			try {
				saveAndUpdate(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				getLogger().error(e.getMessage());
			}
		}
	}
	@Override
	public T obj2Entity(DBObject obj) {
		// TODO Auto-generated method stub
		return convertor.convertToPojo(obj);
	}
}
