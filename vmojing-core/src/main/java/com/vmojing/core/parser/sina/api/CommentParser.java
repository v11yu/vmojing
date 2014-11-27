package com.vmojing.core.parser.sina.api;
import java.util.Date;
import java.util.List;

import com.vmojing.mongodb.domain.Comment;

public interface CommentParser {
	/**
	 * 获取该微博的最新评论微博信息详细，需直接更新的
	 * @param wid 微博id
	 * @param lastUpdateCommentTime 系统最后更新时间
	 * @return 评论列表
	 */
	public List<Comment> getComment(String wid,Date lastUpdateCommentTime);
}
