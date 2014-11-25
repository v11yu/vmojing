package com.vmojing.mongodb.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Clue {
	@Id
	private String id;
	/** 微博详细信息 */
	private Weibo weibo;
	/** 监测开始时间 */
	private Date createAt;
	/** 运行状态，0为正常监测，1为暂停，2为已删除 */
	private Integer operateStatus;
	/** 线索的状态，0为正常线索，1为异常线索 */
	private Integer status;
	/** 最后更新转发微博的时间*/
	private Date lastUpdateRetweetTime;
	/** 最后更新评论微博的时间*/
	private Date lastUpdateCommentTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Weibo getWeibo() {
		return weibo;
	}
	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}
	public Integer getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(Integer operateStatus) {
		this.operateStatus = operateStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getLastUpdateRetweetTime() {
		return lastUpdateRetweetTime;
	}
	public void setLastUpdateRetweetTime(Date lastUpdateRetweetTime) {
		this.lastUpdateRetweetTime = lastUpdateRetweetTime;
	}
	public Date getLastUpdateCommentTime() {
		return lastUpdateCommentTime;
	}
	public void setLastUpdateCommentTime(Date lastUpdateCommentTime) {
		this.lastUpdateCommentTime = lastUpdateCommentTime;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Clue(){
		this.lastUpdateCommentTime = new Date(0);
		this.lastUpdateRetweetTime = new Date(0);
		this.operateStatus = 0;
		this.status = 0;
	}
	@Override
	public String toString() {
		return "Clue [id=" + id + ", weibo=" + weibo + ", createAt=" + createAt
				+ ", operateStatus=" + operateStatus + ", status=" + status
				+ ", lastUpdateRetweetTime=" + lastUpdateRetweetTime
				+ ", lastUpdateCommentTime=" + lastUpdateCommentTime + "]";
	}
	
	
}
