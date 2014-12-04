package com.vmojing.core.parser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import weibo4j.Comments;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;

import com.vmojing.core.parser.WeiboExceptionHandle;
import com.vmojing.core.parser.api.CommentParser;
import com.vmojing.core.parser.convert.CommentConverter;
import com.vmojing.mongodb.business.AccessTokenAllocation;
import com.vmojing.mongodb.domain.Comment;
@Component
@Scope("prototype")
public class CommentParserImpl implements CommentParser{
	private static Logger log = LoggerFactory.getLogger(CommentParserImpl.class);
	private static Integer MaxPage = 10;
	
	@Autowired
	private CommentConverter commentConverter;
	private Comments cm;
	@PostConstruct
	private void initialize(){
		cm = new Comments();
		cm.setToken(AccessTokenAllocation.getAccessToken());
	}
	@Override
	public List<Comment> getComment(String wid, Date lastUpdateCommentTime) {
		// TODO Auto-generated method stub
		List<Comment> res = new ArrayList<Comment>();
		try {
			for(int i=1;i<=MaxPage;i++){
				CommentWapper cw = cm.getCommentById(wid, 200, i);
				List<Comment> segment = getCommentsAfterTime(cw.getComments(),lastUpdateCommentTime);
				if(segment.size() == 0) break;
				res.addAll(segment);
			}
		} catch (WeiboException e) {
			log.error(WeiboExceptionHandle.getErrorString(e,wid+"评论获取错误"));
			return null;
		}
		return res;
	}
	private List<Comment> getCommentsAfterTime(List<weibo4j.model.Comment> cms, Date lastUpdateCommentTime){
		List<Comment> res = new ArrayList<Comment>();
		for(weibo4j.model.Comment c : cms){
			if(c.getCreatedAt().after(lastUpdateCommentTime)){
				res.add(commentConverter.convert(c));
			}
		}
		return res;
	}

}
