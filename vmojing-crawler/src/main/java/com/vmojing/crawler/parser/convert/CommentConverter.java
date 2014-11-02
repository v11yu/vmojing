package com.vmojing.crawler.parser.convert;

import org.springframework.stereotype.Component;

import com.vmojing.mongodb.domain.Comment;
@Component
public class CommentConverter extends Converter<Comment, weibo4j.model.Comment> {

	@Override
	protected void setManualField(weibo4j.model.Comment from, Comment to) {
		// TODO Auto-generated method stub
		UserConverter userConverter = new UserConverter();
		to.setUser(userConverter.convert(from.getUser()));
		to.setWeiboId(from.getStatus().getId());
		if(null != from.getReplycomment())
			to.setReplyCommentId(from.getReplycomment().getId());
	}

}
