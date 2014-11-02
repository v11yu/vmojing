package com.vmojing.mongodb.domain;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vmojing.mongodb.annotation.BasicModel;
import com.vmojing.mongodb.annotation.ManualField;

@Document
@BasicModel
public class Comment {
	@Id
	private Long id;
	
	/** 创建的时间*/
	private Date createdAt;
	/** 评论内容 */
	private String text;
	/** 评论的来源 */
	private String source;
	/** 评论用户 */
	@ManualField
	private User user;
	/** 评论mid */
	private String mid ;
	/** 评论的微博信息ID */
	@ManualField
	private String weiboId;
	/** 评论来源评论，当本评论属于对另一评论的回复时返回此字段 */
	@ManualField
	private Long replyCommentId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getWeiboId() {
		return weiboId;
	}
	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}
	public Long getReplyCommentId() {
		return replyCommentId;
	}
	public void setReplyCommentId(Long replyCommentId) {
		this.replyCommentId = replyCommentId;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", createdAt=" + createdAt + ", text="
				+ text + ", source=" + source + ", user=" + user + ", mid="
				+ mid + ", weiboId=" + weiboId + ", replyCommentId="
				+ replyCommentId + "]";
	}
	
}
