package com.vmojing.mongodb.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Blogger {
	@Id
	private Long id;
	/** 用户详细信息 */
	private Object user;
	/** 检测开始时间*/
	private Date startMonitorTime;
	/** 运行状态，0为已删除，1为暂停，2为正常监测 */
	private Integer operateStatus;
	/** 博主的状态，0为正常博主，1为异常博主 */
	private Integer status;
}
