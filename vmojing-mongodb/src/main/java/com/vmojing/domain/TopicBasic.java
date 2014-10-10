package com.vmojing.domain;


import java.util.Date;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBList;

public class TopicBasic {
	/** mongoDB _id*/
	private ObjectId id;
	/** 话题创建的时间*/
	private Date createAtTime;
	/** 更新的时间*/
	private Date updateTime;
	/** 话题关键字组*/
	private BasicDBList keyWords;
	/** 0为已删除，1为暂停，2为正常监测 */
	private Integer opStatus;
	/** 0为正常话题，1为异常话题 */
	private Integer topicStatus;
	/**话题更新频率 / 分钟*/
	private Integer updateFrequency;
	/** 话题今日微博数量*/
	private Integer todayCount;
	/** 话题总量 */
	private Integer sumCount;
	/** 话题名称 */
	private String topicName;
	/** 0为领导话题，1为部门话题，2是其它话题 */
	private Integer type;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Date getCreateAtTime() {
		return createAtTime;
	}
	public void setCreateAtTime(Date createAtTime) {
		this.createAtTime = createAtTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public BasicDBList getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(BasicDBList keyWords) {
		this.keyWords = keyWords;
	}
	public Integer getOpStatus() {
		return opStatus;
	}
	public void setOpStatus(Integer opStatus) {
		this.opStatus = opStatus;
	}
	public Integer getTopicStatus() {
		return topicStatus;
	}
	public void setTopicStatus(Integer topicStatus) {
		this.topicStatus = topicStatus;
	}
	public Integer getUpdateFrequency() {
		return updateFrequency;
	}
	public void setUpdateFrequency(Integer updateFrequency) {
		this.updateFrequency = updateFrequency;
	}
	public Integer getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(Integer todayCount) {
		this.todayCount = todayCount;
	}
	public Integer getSumCount() {
		return sumCount;
	}
	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "TopicBasic [id=" + id + ", createAtTime=" + createAtTime
				+ ", updateTime=" + updateTime + ", keyWords=" + keyWords
				+ ", opStatus=" + opStatus + ", topicStatus=" + topicStatus
				+ ", updateFrequency=" + updateFrequency + ", todayCount="
				+ todayCount + ", sumCount=" + sumCount + ", topicName="
				+ topicName + ", type=" + type + "]";
	}
	
}
