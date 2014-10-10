package com.vmojing.domain;

import java.util.Date;

import org.bson.types.ObjectId;

public class TopicStatistic {
	/** mongoDB id*/
	private ObjectId id;
	/** topicBasic表Id */
	private ObjectId topicBasicId;
	/** 创建时间*/
	private Date createAtTime;
	/** 正面舆情的数量 */
	private Integer positiveCount;
	/** 中立舆情数量 */
	private Integer neutralCount;
	/** 负面舆情数量 */
	private Integer negativeCount;
	/** 博主参与数量 */
	private Integer bloggerCount;
	/** 僵尸用户数量 */
	private Integer zombieCount;
	/** 地图分布采用JSON格式，如{“北京”：10}*/
	private String mapDistribution;
	/** 男性博主数量 */
	private Integer maleCount;
	/** 女性博主数量 */
	private Integer femaleCount;
	/** 普通博主数量 */
	private Integer ordinaryCount;
	/** 名人博主数量 */
	private Integer celebrityCount;
	/** 政府博主数量 */
	private Integer governmentCount;
	/** 企业认证博主数量 */
	private Integer enterpriseCount;
	/** 媒体数量 */
	private Integer mediaCount;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public ObjectId getTopicBasicId() {
		return topicBasicId;
	}
	public void setTopicBasicId(ObjectId topicBasicId) {
		this.topicBasicId = topicBasicId;
	}
	public Date getCreateAtTime() {
		return createAtTime;
	}
	public void setCreateAtTime(Date createAtTime) {
		this.createAtTime = createAtTime;
	}
	public Integer getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(Integer positiveCount) {
		this.positiveCount = positiveCount;
	}
	public Integer getNeutralCount() {
		return neutralCount;
	}
	public void setNeutralCount(Integer neutralCount) {
		this.neutralCount = neutralCount;
	}
	public Integer getNegativeCount() {
		return negativeCount;
	}
	public void setNegativeCount(Integer negativeCount) {
		this.negativeCount = negativeCount;
	}
	public Integer getBloggerCount() {
		return bloggerCount;
	}
	public void setBloggerCount(Integer bloggerCount) {
		this.bloggerCount = bloggerCount;
	}
	public Integer getZombieCount() {
		return zombieCount;
	}
	public void setZombieCount(Integer zombieCount) {
		this.zombieCount = zombieCount;
	}
	public String getMapDistribution() {
		return mapDistribution;
	}
	public void setMapDistribution(String mapDistribution) {
		this.mapDistribution = mapDistribution;
	}
	public Integer getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Integer maleCount) {
		this.maleCount = maleCount;
	}
	public Integer getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(Integer femaleCount) {
		this.femaleCount = femaleCount;
	}
	public Integer getOrdinaryCount() {
		return ordinaryCount;
	}
	public void setOrdinaryCount(Integer ordinaryCount) {
		this.ordinaryCount = ordinaryCount;
	}
	public Integer getCelebrityCount() {
		return celebrityCount;
	}
	public void setCelebrityCount(Integer celebrityCount) {
		this.celebrityCount = celebrityCount;
	}
	public Integer getGovernmentCount() {
		return governmentCount;
	}
	public void setGovernmentCount(Integer governmentCount) {
		this.governmentCount = governmentCount;
	}
	public Integer getEnterpriseCount() {
		return enterpriseCount;
	}
	public void setEnterpriseCount(Integer enterpriseCount) {
		this.enterpriseCount = enterpriseCount;
	}
	public Integer getMediaCount() {
		return mediaCount;
	}
	public void setMediaCount(Integer mediaCount) {
		this.mediaCount = mediaCount;
	}
	
}
