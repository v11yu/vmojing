package com.vmojing.mongodb.domain;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import weibo4j.model.Source;

import com.vmojing.mongodb.annotation.BasicModel;
import com.vmojing.mongodb.annotation.ManualField;
@Document
@BasicModel
public class Weibo {
	@Id
    private String id;
	
	/** 创建时间*/
	private Date createdAt;
	/** 微博内容*/
	private String text;
	/** 微博mid*/
	@ManualField
	private String mid;
	/** 微博发布自哪个终端 */
	@ManualField
	private String source;
	/** 微图 */
	private String thumbnailPic;
	/** 中图 */
	private String bmiddlePic;
	/** 原图 */
	private String originalPic;
	/**经度 */
	private Double longitude;
	/** 纬度 */
	private Double latitude;
	/** 用户Id*/
	@ManualField
	private User user;
	/** 转发微博Id*/
	@ManualField
	private String retweetWeiboId;
	/** 目前的转发数*/
	private Integer repostsCount;
	/**目前评论数*/
	private Integer commentsCount;
	/** 目前的赞数 */
	private Integer attitudeCount;
	/**地理信息*/
	private String geo;
	
	/** 最后更新的时间*/
	@ManualField
	private Date lastUpdateTime;
	/**话题Id*/
	@ManualField
	private List<ObjectId> topicIds;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getThumbnailPic() {
		return thumbnailPic;
	}
	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}
	public String getBmiddlePic() {
		return bmiddlePic;
	}
	public void setBmiddlePic(String bmiddlePic) {
		this.bmiddlePic = bmiddlePic;
	}
	public String getOriginalPic() {
		return originalPic;
	}
	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRetweetWeiboId() {
		return retweetWeiboId;
	}
	public void setRetweetWeiboId(String retweetWeiboId) {
		this.retweetWeiboId = retweetWeiboId;
	}
	public Integer getRepostsCount() {
		return repostsCount;
	}
	public void setRepostsCount(Integer repostsCount) {
		this.repostsCount = repostsCount;
	}
	public Integer getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}
	public Integer getAttitudeCount() {
		return attitudeCount;
	}
	public void setAttitudeCount(Integer attitudeCount) {
		this.attitudeCount = attitudeCount;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
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
		return "Weibo [id=" + id + ", createdAt=" + createdAt + ", text="
				+ text + ", mid=" + mid + ", source=" + source
				+ ", thumbnailPic=" + thumbnailPic + ", bmiddlePic="
				+ bmiddlePic + ", originalPic=" + originalPic + ", longitude="
				+ longitude + ", latitude=" + latitude + ", user=" + user
				+ ", retweetWeiboId=" + retweetWeiboId + ", repostsCount="
				+ repostsCount + ", commentsCount=" + commentsCount
				+ ", attitudeCount=" + attitudeCount + ", geo=" + geo
				+ ", lastUpdateTime=" + lastUpdateTime + ", topicIds="
				+ topicIds + "]";
	}

	
}
