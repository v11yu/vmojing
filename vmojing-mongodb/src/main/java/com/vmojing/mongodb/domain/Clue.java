package com.vmojing.mongodb.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Clue {
	@Id
	private Long id;
	/** 微博详细信息 */
	private Weibo weibo;
	/** 监测开始时间 */
	private Date startMonitorTime;
	/** 运行状态，0为已删除，1为暂停，2为正常监测 */
	private Integer operateStatus;
	/** 线索的状态，0为正常线索，1为异常线索 */
	private Integer status;
}
