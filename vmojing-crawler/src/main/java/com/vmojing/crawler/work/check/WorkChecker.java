package com.vmojing.crawler.work.check;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.mongodb.annotation.Frequent;
import com.vmojing.mongodb.annotation.LastTime;

public class WorkChecker implements CheckStrategy {
	private static final Logger log = LoggerFactory.getLogger(WorkChecker.class);

	@Override
	public <T> boolean check(T t) {
		String frequentName = null;
		String lastTimeName = null;
		Integer fre = 0;
		Date date = null;
		for (Field f : t.getClass().getDeclaredFields()) {
			if (f.isAnnotationPresent(Frequent.class)) {
				frequentName = f.getName();
			}
			if (f.isAnnotationPresent(LastTime.class)) {
				lastTimeName = f.getName();
			}
		}
		if(lastTimeName == null || frequentName == null){
			log.warn("警告:没有相关声明"+t.getClass().getName()+" have no annotation!!check failed and just return true!");
			return true;
		}
		try {
			fre = (Integer) PropertyUtils.getProperty(t, frequentName);
			date = (Date) PropertyUtils.getProperty(t, lastTimeName);
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long needMin = fre * 60 * 1000;
		long usedMin = new Date().getTime() - date.getTime();
		if(usedMin < needMin){
			log.info("暂不需要更新，等待下次操作,更新间隔:"+needMin/60/1000+"分钟,已用时间:"+usedMin/60/1000+"分钟"+t);
			return false;
		}
		return true;
	}

}
