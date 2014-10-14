package com.vmojing.crawler.monitor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.dao.impl.TopicBasicDAOImpl;

import weibo4j.model.Status;


public class SinaTopicMonitor {
	/** 登陆日志 */
	private final static Logger log = LoggerFactory.getLogger(SinaTopicMonitor.class);
	/** 话题基础表DAO */
	private TopicBasicDAOImpl tbdao = new TopicBasicDAOImpl();
	public List<Status> getWeiboList(List<String> wids){
		return null;
	}
	public void saveToDataBase(){
		
	}
	public void monitor(){
		Map<ObjectId, String> topicIdNameMap = tbdao.getTopicNameIdMap();
		Iterator<Entry<ObjectId, String>> iter = topicIdNameMap.entrySet().iterator();
		ExecutorService pools = Executors.newCachedThreadPool();
		while(iter.hasNext()){
			Entry<ObjectId, String> entry = iter.next();
			//new SinaTopicWorker()
		}
	}
}
