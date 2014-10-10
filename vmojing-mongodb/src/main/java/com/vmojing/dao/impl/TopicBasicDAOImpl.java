package com.vmojing.dao.impl;


public class TopicBasicDAOImpl extends BasicDAO{
	/** 默认表单名 */
	private static final String DEFAULT_COLLECTION_NAME = "topic_basic";
	
	public TopicBasicDAOImpl(){
		super(DEFAULT_COLLECTION_NAME);
	}
	public TopicBasicDAOImpl(String collectionName){
		super(collectionName);
	}
	
}
