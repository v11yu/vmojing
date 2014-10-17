package com.vmojing.mongodb.domain;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Weibo {
	@Id
    private Long id;
	
	/** 创建时间*/
	private Date createAtTime;
	/** 微博内容*/
	private String text;
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
	/** 最后更新的时间*/
	private Date lastUpdateTime;
	/**话题Id*/
	private List<ObjectId> topicIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateAtTime() {
		return createAtTime;
	}
	public void setCreateAtTime(Date createAtTime) {
		this.createAtTime = createAtTime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public List<ObjectId> getTopicIds() {
		return topicIds;
	}
	public void setTopicIds(List<ObjectId> topicIds) {
		this.topicIds = topicIds;
	}
	@Override
	public String toString() {
		return "Weibo [id=" + id + ", createAtTime=" + createAtTime + ", text="
				+ text + ", source=" + source + ", thumbnailPicture="
				+ thumbnailPicture + ", bmiddlePicture=" + bmiddlePicture
				+ ", originalPicture=" + originalPicture + ", longitude="
				+ longitude + ", latitude=" + latitude + ", userId=" + userId
				+ ", retweetWeiboId=" + retweetWeiboId + ", retweetCount="
				+ retweetCount + ", commentCount=" + commentCount
				+ ", attitudeCount=" + attitudeCount + ", monitoredTime="
				+ monitoredTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", topicIds=" + topicIds + "]";
	}
	
	
}
