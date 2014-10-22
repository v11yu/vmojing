package com.vmojing.mongodb.business;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBusiness {
	/** 日志容器*/
	@SuppressWarnings("rawtypes")
	private static final Map<Class,Logger> loggers = new HashMap<Class,Logger>();
	/**
	 * 获取当前类的日志，用于继承情况：子类或父类<p/>
	 * get Logger for current class for subclass or superclass
	 * @return 
	 */
	protected Logger getLogger()
	{
	    Logger logger = null;
	    if (AbstractBusiness.loggers.containsKey(this.getClass())) {
	        logger = AbstractBusiness.loggers.get(this.getClass());
	    } else {
	        logger = LoggerFactory.getLogger(this.getClass());
	        AbstractBusiness.loggers.put (this.getClass(), logger);
	    }
	    return logger;
	}
}
