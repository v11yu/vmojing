package com.vmojing.mongodb.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.mongodb.domain.Blogger;


public abstract class AbstractBusiness<T> {
	/** 日志容器*/
	private static final Map<Class<?>,Logger> loggers = new HashMap<Class<?>,Logger>();
	/**
	 * 获取当前类的日志，用于继承情况：子类或父类<p/>
	 * get Logger for current class for subclass or superclass
	 * @return 
	 */
	protected Logger getLogger(){
		Class<?> type = this.getClass();
		if(!loggers.containsKey(type)){
			Logger logger = LoggerFactory.getLogger(type);
			AbstractBusiness.loggers.put(type, logger);
		}
		return loggers.get(type);
	}
//	public abstract boolean save(T t);
//	public abstract List<T> getAll();
//	public abstract T getById(String id);
//	public abstract boolean delete(String id);
}
