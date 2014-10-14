package com.vmojing.mongodb.domain;

public class RemoveWeiboField {
	/**
	 * 0为不处于预警状态，1为处于黄色预警状态，2为处于橙色预警状态，3为处于红色预警状态，这个字段用户微博预警中
	 */
	private Integer status;
	/**用户质量*/
	private Double userQuality;
	/**用户质量等级*/
	private Integer userQualityRank;
	/** -1,0,1分别代表负面/中性/正面情绪 */
	private Integer emotionStatus;
	/**预测转发数*/
	private Integer predictRetweetCount;
	/**预测评论数*/
	private Integer predictCommentCount;
	/** 如{“2013-11-01 15:00:00”:”1,1,1”, “2013-11-01 15:00:00”:”2,2,1” }),在每个时间点上时的转发数、评论数、赞数*/
	private String updateRecord;

}
