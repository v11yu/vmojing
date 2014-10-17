package com.vmojing.mongodb.domain;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
	@Id
	private Long id;
	
	/** 创建的时间*/
	private Date createAtTime;
	/** 评论内容 */
	private String text;
	/** 评论的来源 */
	private String source;
	/** 评论用户 */
	private Object user;
	/** 评论mid */
	private String mid ;
	/** 字符串型的评论ID */
	private String idstr;
	/** 评论的微博信息ID */
	private Long weiboId;
	/** 评论来源评论，当本评论属于对另一评论的回复时返回此字段 */
	private Long replyCommentId;
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
	public Object getUser() {
		return user;
	}
	public void setUser(Object user) {
		this.user = user;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getIdstr() {
		return idstr;
	}
	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}
	public Long getWeiboId() {
		return weiboId;
	}
	public void setWeiboId(Long weiboId) {
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
		return "Comment [id=" + id + ", createAtTime=" + createAtTime
				+ ", text=" + text + ", source=" + source + ", user=" + user
				+ ", mid=" + mid + ", idstr=" + idstr + ", weiboId=" + weiboId
				+ ", replyCommentId=" + replyCommentId + "]";
	}
	
}
