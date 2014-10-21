package com.vmojing.mongodb.repository;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.QueryOperators;
/**
 * 查询构造类
 * <pre>
 * DBQuery query = new DBQuery();
 * query.lessThan("key1", "value1");
 * query.greaterThan("key2", "v2");
 * query.equalsOperation("key3","v3");
 * query.getQuery()
 * </pre>
 * you will get the query like this:
 * <pre>
 * { "key1" : { "$lt" : "value1"} , "key2" : { "$gt" : "v2"} , "key3" : "v3"}
 * </pre>
 * @author v11
 * @date 2014年10月20日
 * @version 1.0
 */
public class DBQuery {
	private QueryBuilder query;
	public DBQuery(){
		query = QueryBuilder.start();
	}
	/**
	 * Equivalent to the $gte operator and return this DBQuery instance
	 * @param key MongoDB document key
	 * @param value Value to query
	 * @return <code>DBQuery</code>
	 */
	public DBQuery greaterThanEquals(String key , Object value){
		query.put(key).greaterThanEquals(value);
		return this;
	}
	/**
	 * Equivalent of the find({key:value}) and return this DBQuery instance
	 * @param key MongoDB document key
	 * @param value Value to query
	 * @return <code>DBQuery</code>
	 */
	public DBQuery equalsOperation(String key , Object value){
		query.put(key).is(value);
		return this;
	}
	/**
	 * Equivalent to the $gt operator and return this DBQuery instance
	 * @param key MongoDB document key
	 * @param value Value to query
	 * @return <code>DBQuery</code>
	 */
	public DBQuery greaterThan(String key , Object value){
		query.put(key).greaterThan(value);
		return this;
	}
	/**
     * Equivalent to the $lt operand and return this DBQuery instance
     * @param key MongoDB document key
	 * @param value Value to query
	 * @return <code>DBQuery</code>
	 */
    public DBQuery lessThan(String key , Object value) {
    	query.put(key).lessThan(value);
    	return this;
    }
	
    /**
     * Equivalent to the $lte operand and return this DBQuery instance
     * @param key MongoDB document key
	 * @param value Value to query
	 * @return <code>DBQuery</code>
	 */
    public DBQuery lessThanEquals(String key , Object value){
    	query.put(key).lessThanEquals(value);
        return this;
    }
    /**
     * clear and create a new <code>DBQuery</code>
     */
    public void clear(){
    	query = QueryBuilder.start();
    }
    /**
     * Creates a DBObject query to be used for the driver's find operations
     * @return a DBObject query instance
     */
	public DBObject getQuery(){
		return query.get();
	}
	
}
