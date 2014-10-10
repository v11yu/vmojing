package com.vmojing.domain.constant;
public class TopicBasicConstant {
	/** 话题创建的时间*/
	public static final String CREATE_AT_TIME_FIELD = "ca";
	/** mongoDB _id*/
	public static final String ID_FIELD = "_id";
	/** 话题关键字组*/
	public static final String KEY_WORDS_FIELD = "kw";
	/** 0为已删除，1为暂停，2为正常监测 */
	public static final String OP_STATUS_FIELD = "os";
	/** 话题总量 */
	public static final String SUM_COUNT_FIELD = "sc";
	/** 0为正常话题，1为异常话题 */
	public static final String TOPIC_STATUS_FIELD = "status";
	/** 话题今日微博数量*/
	public static final String TODAY_COUNT_FIELD = "tc";
	/** 话题名称 */
	public static final String TOPIC_NAME_FIELD = "tn";
	/** 0为领导话题，1为部门话题，2是其它话题 */
	public static final String TYPE_FILED = "type";
	/**话题更新频率 / 分钟*/
	public static final String UPDATE_FREQUENCY_FILED = "uf";
	/** 更新的时间*/
	public static final String UPDATE_TIME_FILED = "ut";

}
