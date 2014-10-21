package com.vmojing.crawler.parser;

import java.util.Date;
import java.util.List;

import com.vmojing.mongodb.domain.Comment;

public interface CommentParser {
	/**
	 * 获取该微博的最新评论微博信息详细
	 * @param wid 微博id
	 * @param lastUpdateCommentTime 系统最后更新时间
	 * @return 评论列表
	 */
	public List<Comment> getComment(Long wid,Date lastUpdateCommentTime);
}