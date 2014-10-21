package com.vmojing.mongodb.repository;

import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public interface DAO{
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
	
	
}
