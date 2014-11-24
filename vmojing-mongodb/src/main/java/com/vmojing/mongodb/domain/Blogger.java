package com.vmojing.mongodb.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Blogger {
	@Id
	private String id;
	/** 用户详细信息 */
	private User user;
	/** 检测开始时间*/
	private Date createAt;
	/** 运行状态，0为正常监测，1为暂停，2为已删除 */
	private Integer operateStatus;
	/** 博主的状态，0为正常博主，1为异常博主 */
	private Integer status;
	/** 最后更新粉丝的时间*/
	private Date lastUpdateFansTime;
	/** 最后更新微博的时间*/
	private Date lastUpdateWeiboTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Date getLastUpdateFansTime() {
		return lastUpdateFansTime;
	}
	public void setLastUpdateFansTime(Date lastUpdateFansTime) {
		this.lastUpdateFansTime = lastUpdateFansTime;
	}
	public Date getLastUpdateWeiboTime() {
		return lastUpdateWeiboTime;
	}
	public void setLastUpdateWeiboTime(Date lastUpdateWeiboTime) {
		this.lastUpdateWeiboTime = lastUpdateWeiboTime;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	@Override
	public String toString() {
		return "Blogger [id=" + id + ", user=" + user + ", createAt="
				+ createAt + ", operateStatus=" + operateStatus + ", status="
				+ status + ", lastUpdateFansTime=" + lastUpdateFansTime
				+ ", lastUpdateWeiboTime=" + lastUpdateWeiboTime + "]";
	}
	
	
}
