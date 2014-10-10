package com.vmojing.domain.constant;



public class WeiboConstant {
	public static String ID = "_id";
	/** 创建时间*/
	public static String CREATE_AT_TIME = "ca";
	/** 微博内容*/
	public static String CONTENT = "text";
	/** 微博发布自哪个终端 */
	public static String SOURCE = "sr";
	/** 微图 */
	public static String THUMBNAIL_PICTURE = "tp";
	/** 中图 */
	public static String BMIDDLE_PICTURE = "bp";
	/** 原图 */
	public static String ORIGINAL_PICTURE = "op";
	/** 经度 */
	public static String LONGITUDE = "lo";
	/** 纬度 */
	public static String LATITUDE = "la";
	/** 用户Id*/
	public static String USER_ID = "uid";
	/** 转发微博Id*/
	public static String RETWEET_WEIBO_ID = "rwid";
	/** 目前的转发数*/
	public static String RETWEET_COUNT = "rc";
	/**目前评论数*/
	public static String COMMENT_COUNT = "cc";
	/** 目前的赞数 */
	public static String ATTITUDE_COUNT = "ac";
	/** 监测开始时间 */
	public static String MONITORED_TIME = "mt";
	/** 如{“2013-11-01 15:00:00”:”1,1,1”, “2013-11-01 15:00:00”:”2,2,1” }),在每个时间点上时的转发数、评论数、赞数*/
	public static String UPDATE_RECORD = "ur";
	/**预测转发数*/
	public static String PREDICT_RETWEET_COUNT = "prc";
	/**预测评论数*/
	public static String PREDICT_COMMENT_COUNT = "pcc";
	/**话题Id*/
	public static String TOPIC_ID = "tid";
	/**
	 * 0为不处于预警状态，1为处于黄色预警状态，2为处于橙色预警状态，3为处于红色预警状态，这个字段用户微博预警中
	 */
	public static String STATUS = "status";
	/**用户质量*/
	public static String USER_QUALITY = "uq";
	/**用户质量等级*/
	public static String USER_QUALITY_RANK = "uqr";
	/** -1,0,1分别代表负面/中性/正面情绪 */
	public static String EMOTION_STATUS = "es";
}
