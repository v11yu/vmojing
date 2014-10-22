	package com.vmojing.mongodb.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

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
	protected DBCollection Collection;
	/**
	 * 构造函数<p/>Example:
	 * <pre>
	 * new BasicRepository(Topic.class)
	 * </pre>
	 * @param entityType 对应的document类
	 * 
	 */
	
	public BasicRepository(Class<?> entityType,DBConvertor<T> convertor){
		getLogger().info("create a Object of BasicRepository,type of "+entityType);
		this.entityType = entityType;
		this.convertor = convertor;
	}
	@PostConstruct
	private void init(){
		Collection = MongoDbUtil.getCollection(getCollectionName());
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
		getLogger().info("operation of creating "+entityType);
        if (!mongoTemplate.collectionExists(entityType)) {
        	getLogger().info("create collection with a name based on "+entityType);
            mongoTemplate.createCollection(entityType);
        }
    }
	/**
	 * Drop the collection with the name indicated by the entity class. 
	 */
	public void dropCollection(){
		getLogger().info("operation of dropping "+entityType);
		if (mongoTemplate.collectionExists(entityType)) {
			getLogger().info("drop collection with a name based on "+entityType);
            mongoTemplate.dropCollection(entityType);
        }
	}
	@Override
	public DBCursor findByAll() {
		// TODO Auto-generated method stub
		DBCursor cursor = Collection.find();
		getLogger().info("findByAll from "+getCollectionName()+" count:"+cursor.count());
		return Collection.find();
	}
	@Override
	public DBCursor findQuery(DBObject query) {
		// TODO Auto-generated method stub
		getLogger().info("findQuery :"+query+" from "+getCollectionName());
		return Collection.find(query);
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
        Collection.update(query, new BasicDBObject("$set", updateObj));
		
	}
	@Override
	public int countAndClose(DBCursor cursor) {
		// TODO Auto-generated method stub
		int res = -1;
		try{
			res = cursor.size();
		}catch(Exception e){
			getLogger().error("获取所有数据出错"+e.toString());
		}finally{
			if(cursor != null) cursor.close();
		}
		return res;
	}
	@Override
	public T findOne() {
		// TODO Auto-generated method stub
		DBObject obj = Collection.findOne();
		return convertor.convertToPojo(obj);
	}
	@Override
	public void update(T obj, String keyNames) {
		// TODO Auto-generated method stub
		update(convertor.convertToDB(obj),keyNames);
	}
	@Override
	public void saveAndUpdate(T obj) {
		// TODO Auto-generated method stub
		getLogger().info("saveAndUpdate :"+obj+" in "+getCollectionName());
		mongoTemplate.save(obj);
	}
	@Override
	public void saveAndUpdate(DBObject obj) {
		// TODO Auto-generated method stub
		mongoTemplate.save(convertor.convertToPojo(obj));
	}

	

}
