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
	/**话题Id*/
	private List<ObjectId> topicIds;
	
}
