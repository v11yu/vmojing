package com.vmojing.mongodb.domain;

public class RemoveTopicField {
	/** 0为正常话题，1为异常话题 */
	private Integer topicStatus;
	/** 话题今日微博数量*/
	private Integer todayCount;
	/** 话题总量 */
	private Integer sumCount;
}
