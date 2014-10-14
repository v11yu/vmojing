package com.vmojing.mongodb.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBList;

@Document
public class Topic {
	@Id
	private ObjectId id;
	
	/** 话题创建的时间*/
	private Date createAtTime;
	/** 最后更新的时间*/
	private Date lastUpdateTime;
	/** 0为已删除，1为暂停，2为正常监测 */
	private Integer operateStatus;
	/**话题更新频率 / 分钟*/
	private Integer updateFrequency;
	/** 话题名称 */
	private String topicName;
	/** 话题类别：0为领导话题，1为部门话题，2是其它话题 */
	private Integer type;
	public Topic(Date createAtTime, Date lastUpdateTime, Integer operateStatus,
			Integer updateFrequency, String topicName, Integer type) {
		super();
		this.createAtTime = createAtTime;
		this.lastUpdateTime = lastUpdateTime;
		this.operateStatus = operateStatus;
		this.updateFrequency = updateFrequency;
		this.topicName = topicName;
		this.type = type;
	}
	
}
