package com.vmojing.mongodb.domain;

import java.util.Date;




import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.mongodb.BasicDBList;
import com.vmojing.mongodb.annotation.Frequent;
import com.vmojing.mongodb.annotation.LastTime;

@Document
public class Topic {
	@Id
	private ObjectId id;
	
	/** 话题创建的时间*/
	private Date createAtTime;
	/** 最后更新的时间*/
	private Date lastUpdateTime;
	/** 0为正常监测，1为暂停，2为已删除 */
	private Integer operateStatus;
	/** 话题名称 */
	private String topicName;
	/** 话题类别：0为领导话题，1为部门话题，2是其它话题 */
	private Integer type;
	/** 采集话题的最早时间域,即采集beginTime之后的话题 */
	@DateTimeFormat(iso=ISO.DATE)
	private Date beginTime;
	/** 初始化采集，已经到达的时间*/
	private Date initAtTime;
	/** 已采集的总数 */
	private Integer sum;
	/**更新频率 / 分钟*/
	@Frequent
	private Integer updateFrequency;
	/**最后操作时间 */
	@LastTime
	private Date lastTime;
	public Topic(){
		this.createAtTime = new Date();
		this.operateStatus = 0;
		this.lastUpdateTime = new Date(0);
		this.initAtTime = new Date(0);
		this.sum = 0;
		this.updateFrequency = 20;
		this.lastTime = new Date(0);
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

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

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(Integer operateStatus) {
		this.operateStatus = operateStatus;
	}

	public Integer getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(Integer updateFrequency) {
		this.updateFrequency = updateFrequency;
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
	public Date getInitAtTime() {
		return initAtTime;
	}
	public void setInitAtTime(Date initAtTime) {
		this.initAtTime = initAtTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Topic( Date createAtTime, Date lastUpdateTime,
			Integer operateStatus, Integer updateFrequency, String topicName,
			Integer type, Date beginTime) {
		super();
		this.createAtTime = createAtTime;
		this.lastUpdateTime = lastUpdateTime;
		this.operateStatus = operateStatus;
		this.updateFrequency = updateFrequency;
		this.topicName = topicName;
		this.type = type;
		this.beginTime = beginTime;
		this.initAtTime = new Date(0);
		this.sum = 0;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", createAtTime=" + createAtTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", operateStatus="
				+ operateStatus + ", topicName=" + topicName + ", type=" + type
				+ ", beginTime=" + beginTime + ", initAtTime=" + initAtTime
				+ ", sum=" + sum + ", updateFrequency=" + updateFrequency
				+ ", lastTime=" + lastTime + "]";
	}

}
