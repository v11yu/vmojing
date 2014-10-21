package com.vmojing.mongodb.repository;

import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public interface DAO<T>{
	/** 获取查询全部记录 */
	public DBCursor findByAll();
	/**
	 * 根据<code> DBObject query</code> 查询数据库
	 * @param query 查询语句
	 * @return 
	 * @see DBQuery
	 * @see QueryBuilder
	 */
	public DBCursor findQuery(DBObject query);
	/**
	 * 根据<code> DBQuery query</code> 查询数据库
	 * @param query 查询语句
	 * @return 
	 * @see DBQuery
	 */
	public DBCursor findQuery(DBQuery query);
	/**
	 * 根据<code> QueryBuilder query</code> 查询数据库
	 * @param query 查询语句
	 * @return 
	 * @see QueryBuilder
	 */
	public DBCursor findQuery(QueryBuilder query);
	/** 
	 * 保存一条记录
	 */
	public void save(DBObject obj);
	/** 
	 * 保存一条记录
	 */
	public void save(T obj);
	/** 
	 * 保存所有记录
	 */
	public void saveAll(DBCursor cursor);
	/**
	 * 删除该表所有记录
	 */
	public void dropAll();
	/**
	 * 更新数据
	 * @param obj 变动后的<code>DBObjet</code>的完整信息
	 * @param keyNames 需要变动的字段名，多个字段之间用,隔开<p/>
	 * Example
	 * <pre>
	 * keyName = "time,age,firendCount"
	 * </pre>
	 * 
	 */
	public void update(DBObject obj,String keyNames);
	
}
