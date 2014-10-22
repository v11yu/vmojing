package com.vmojing.mongodb.repository;

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.vmojing.mongodb.repository.core.Converter;


public class DBConvertor<T> implements Converter<DBObject, T>{
	@Autowired
    private MongoTemplate mongoTemplate;
	/** 注册的document类 */
	private final Class<?> typeParameterClass;
	public DBConvertor(Class<?> typeParameterClass){
		this.typeParameterClass = typeParameterClass;
	}
	@Override
	public T convertToPojo(DBObject obj) {
		// TODO Auto-generated method stub
		return (T) mongoTemplate.getConverter().read(typeParameterClass, obj);
	}

	@Override
	public DBObject convertToDB(T source) {
		// TODO Auto-generated method stub
		DBObject obj = new BasicDBObject();
		mongoTemplate.getConverter().write(source, obj);
		return removeNullField(obj);
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
