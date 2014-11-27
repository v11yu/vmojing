package com.vmojing.mongodb.repository.core;

import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;
import com.vmojing.mongodb.repository.DBQuery;

public interface DAO<T>{
	/** 获取查询全部记录 */
	public DBCursor findByAll();
	/**
	 *  获取查询记录数量,并关闭DBCursor
	 * @param cursor
	 * @return
	 */
	public int countAndClose(DBCursor cursor);
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
	/**
	 * @see DAO#update(DBObject, String)
	 */
	public void update(T obj,String keyNames);
	/**
	 * 根据id,如果不存在就save，否则update
	 * @param obj
	 * @throws Exception 
	 */
	public void saveAndUpdate(T obj) throws Exception;
	/**
	 * 根据id,如果不存在就save，否则update
	 * @param obj
	 */
	public void saveAndUpdate(DBObject obj);
	/**
	 * 任意获取一个
	 */
	public T findOne();
	/**
	 * 从cursor中获取第一个实体，一般用于查找仅有一个的时候<p />
	 * 并关闭cursor流
	 * @param cursor 
	 * @return if Cusor.size>0,返回第一个元素，or 返回 null
	 */
	public T findOne(DBCursor cursor);
	/**
	 * 查找通过主键Id
	 * @param id 主键值
	 * @return 对应的值,or Null if no exist
	 */
	public T findById(Object id);
	public WriteResult dropById(Object id);
}
