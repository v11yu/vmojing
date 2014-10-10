package com.vmojing.dao.impl;


public class TopicStatisticDAOImpl extends BasicDAO{
	/** 默认表单名 */
	private static final String DEFAULT_COLLECTION_NAME = "topic_statistic";
	
	public TopicStatisticDAOImpl(){
		super(DEFAULT_COLLECTION_NAME);
	}
	public TopicStatisticDAOImpl(String collectionName){
		super(collectionName);
	}
}
