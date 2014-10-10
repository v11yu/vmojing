package com.vmojing.domain;

import java.util.Date;

import org.bson.types.ObjectId;

public class Weibo {
	private ObjectId id;
	/** 创建时间*/
	private Date createAtTime;
	/** 微博内容*/
	private String content;
	/** 微博发布自哪个终端 */
	private String source;
	/** 微图 */
	private String thumbnailPicture;
	/** 中图 */
	private String bmiddlePicture;
	/** 原图 */
	private String originalPicture;
	/**经度 */
	private Double longitude;
	/** 纬度 */
	private Double latitude;
	/** 用户Id*/
	private Long userId;
	/** 转发微博Id*/
	private Long retweetWeiboId;
	/** 目前的转发数*/
	private Integer retweetCount;
	/**目前评论数*/
	private Integer commentCount;
	/** 目前的赞数 */
	private Integer attitudeCount;
	/** 监测开始时间 */
	private Date monitoredTime;
	/** 如{“2013-11-01 15:00:00”:”1,1,1”, “2013-11-01 15:00:00”:”2,2,1” }),在每个时间点上时的转发数、评论数、赞数*/
	private String updateRecord;
	/**预测转发数*/
	private Integer predictRetweetCount;
	/**预测评论数*/
	private Integer predictCommentCount;
	/**话题Id*/
	private ObjectId topicId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getThumbnailPicture() {
		return thumbnailPicture;
	}
	public void setThumbnailPicture(String thumbnailPicture) {
		this.thumbnailPicture = thumbnailPicture;
	}
	public String getBmiddlePicture() {
		return bmiddlePicture;
	}
	public void setBmiddlePicture(String bmiddlePicture) {
		this.bmiddlePicture = bmiddlePicture;
	}
	public String getOriginalPicture() {
		return originalPicture;
	}
	public void setOriginalPicture(String originalPicture) {
		this.originalPicture = originalPicture;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRetweetWeiboId() {
		return retweetWeiboId;
	}
	public void setRetweetWeiboId(Long retweetWeiboId) {
		this.retweetWeiboId = retweetWeiboId;
	}
	public Integer getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(Integer retweetCount) {
		this.retweetCount = retweetCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getAttitudeCount() {
		return attitudeCount;
	}
	public void setAttitudeCount(Integer attitudeCount) {
		this.attitudeCount = attitudeCount;
	}
	public Date getMonitoredTime() {
		return monitoredTime;
	}
	public void setMonitoredTime(Date monitoredTime) {
		this.monitoredTime = monitoredTime;
	}
	public String getUpdateRecord() {
		return updateRecord;
	}
	public void setUpdateRecord(String updateRecord) {
		this.updateRecord = updateRecord;
	}
	public Integer getPredictRetweetCount() {
		return predictRetweetCount;
	}
	public void setPredictRetweetCount(Integer predictRetweetCount) {
		this.predictRetweetCount = predictRetweetCount;
	}
	public Integer getPredictCommentCount() {
		return predictCommentCount;
	}
	public void setPredictCommentCount(Integer predictCommentCount) {
		this.predictCommentCount = predictCommentCount;
	}
	public ObjectId getTopicId() {
		return topicId;
	}
	public void setTopicId(ObjectId topicId) {
		this.topicId = topicId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getUserQuality() {
		return userQuality;
	}
	public void setUserQuality(Double userQuality) {
		this.userQuality = userQuality;
	}
	public Integer getUserQualityRank() {
		return userQualityRank;
	}
	public void setUserQualityRank(Integer userQualityRank) {
		this.userQualityRank = userQualityRank;
	}
	public Integer getEmotionStatus() {
		return emotionStatus;
	}
	public void setEmotionStatus(Integer emotionStatus) {
		this.emotionStatus = emotionStatus;
	}
	@Override
	public String toString() {
		return "Weibo [id=" + id + ", createAtTime=" + createAtTime
				+ ", content=" + content + ", source=" + source
				+ ", thumbnailPicture=" + thumbnailPicture
				+ ", bmiddlePicture=" + bmiddlePicture + ", originalPicture="
				+ originalPicture + ", longitude=" + longitude + ", latitude="
				+ latitude + ", userId=" + userId + ", retweetWeiboId="
				+ retweetWeiboId + ", retweetCount=" + retweetCount
				+ ", commentCount=" + commentCount + ", attitudeCount="
				+ attitudeCount + ", monitoredTime=" + monitoredTime
				+ ", updateRecord=" + updateRecord + ", predictRetweetCount="
				+ predictRetweetCount + ", predictCommentCount="
				+ predictCommentCount + ", topicId=" + topicId + ", status="
				+ status + ", userQuality=" + userQuality
				+ ", userQualityRank=" + userQualityRank + ", emotionStatus="
				+ emotionStatus + "]";
	}
	
	
	
}
