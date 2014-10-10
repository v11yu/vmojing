package com.vmojing.domain.convert;


import java.util.Iterator;
import java.util.Map.Entry;

import com.mongodb.DBObject;

/**
 * 转换类接口
 * @author v11
 * @date 2014年9月30日
 * @param <T> 待转换的domain
 */
public abstract class Converter<T> {

	/**
	 * conver DBObject to some domain object
	 * @param obj mongoDB obj
	 * @return T
	 */
	public abstract T convert(DBObject obj);

	/**
	 * conver obj to DBObject
	 * 
	 * @param from
	 *            domain obj
	 * @return DBObject
	 */
	protected abstract DBObject convertToDBObject(T from);
	/**
	 * conver obj to DBObject
	 * and remove null field of obj
	 * @param from
	 *            domain obj
	 * @return DBObject
	 */
	public DBObject toDBObject(T from) {
		return removeNullField(convertToDBObject(from));

	}
	private DBObject removeNullField(DBObject obj){
		@SuppressWarnings("unchecked")
		Iterator<Entry<String,Object>> iter = obj.toMap().entrySet().iterator();
		while(iter.hasNext()){
			Entry<String,Object> en = iter.next();
			if(en.getValue() == null){
				obj.removeField(en.getKey());
			}
		}
		return obj;
	}
}
