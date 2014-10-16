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
	/** 评论的微博信息字段 */
	private Object status;
	/** 评论来源评论，当本评论属于对另一评论的回复时返回此字段 */
	private Object replyComment;
}
