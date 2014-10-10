package com.vmojing.dao;

import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public interface DAO{
	/** 获取查询全部记录 */
	public DBCursor findByAll();
	/**
	 * 获取小于等于value的记录
	 * @param valueName 字段名
	 * @param value 值
	 * @return 返回条件查询结果
	 */
	public DBCursor findByValueLessThanEquals(String valueName,Object value);
	/**
	 * 获取大于等于value的记录
	 * @param valueName 字段名
	 * @param value 值
	 * @return 返回条件查询结果
	 */
	public DBCursor findByValueGreaterThanEquals(String valueName,Object value);
	/**
	 * 获取等于value的记录
	 * @param valueName 字段名
	 * @param value 值
	 * @return 返回条件查询结果
	 */
	public DBCursor findByValueEquals(String valueName,Object value);
	/** 
	 * 保存一条记录
	 */
	public void save(DBObject obj);
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
	 * @param 更改后的<code>DBObjet</code>
	 * @param keyNames 需要变动的多个字段组<br/>
	 * 
	 */
	public void update(DBObject obj, List<String> keyNames);
	/**
	 * 更新数据
	 * @param 更改后的<code>DBObjet</code>
	 * @param keyName 需要变动的单个字段名<br/>
	 * 
	 */
	public void update(DBObject obj,String keyName);
	/**
	 * 对已有的<code>DBCursor</code>进行排序
	 * @param keyName 字段名
	 * @param op 1：asc，-1：desc
	 * @param cursor 待排序的DBCursor
	 * @return
	 */
	public DBCursor sort(String keyName,int op,DBCursor cursor);
	/**
	 * 分页查找
	 * @param itemCount 每页个数
	 * @param pageNum 页数
	 * @param cursor 待分页的DBCursor
	 * @return
	 */
	public DBCursor findByPage(Integer itemCount,Integer pageNum,DBCursor cursor);
	/**
	 * Returns a single object from this collection.
	 * @return the object found, or null if the collection is empty
	 */
	public DBObject findOne();
}
