package com.vmojing.mongodb.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;



public class BasicRepository<T> {
	/** 日志容器*/
	@SuppressWarnings("rawtypes")
	private static final Map<Class,Logger> loggers = new HashMap<Class,Logger>();
	@Autowired
    private MongoTemplate mongoTemplate;
	/** 注册的document类 */
	private final Class<?> typeParameterClass;
	protected DBCollection Collection;
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
	 * 构造函数<p/>Example:
	 * <pre>
	 * new BasicRepository(Topic.class)
	 * </pre>
	 * @param typeParameterClass 对应的document类
	 * 
	 */
	public BasicRepository(Class<?> typeParameterClass){
		getLogger().info("create a Object of BasicRepository,type of "+typeParameterClass);
		this.typeParameterClass = typeParameterClass;
		this.Collection = MongoDbUtil.getCollection(getCollectionName());
	}
	/**
	 * The collection name used for the specified class.
	 * @return
	 */
	public String getCollectionName(){
		return mongoTemplate.getCollectionName(typeParameterClass);
	}
	/**
	 * Create an uncapped collection with a name based on the provided entity class.
	 * @param t
	 */
	public void createCollection() {
		getLogger().info("get collection with a name based on "+typeParameterClass);
        if (!mongoTemplate.collectionExists(typeParameterClass)) {
        	getLogger().info("create collection with a name based on "+typeParameterClass);
            mongoTemplate.createCollection(typeParameterClass);
        }
    }
	/**
	 * Drop the collection with the name indicated by the entity class. 
	 */
	public void dropCollection(){
		getLogger().info("drop collection with a name based on "+typeParameterClass);
		if (mongoTemplate.collectionExists(typeParameterClass)) {
            mongoTemplate.dropCollection(typeParameterClass);
        }
	}
	

}
