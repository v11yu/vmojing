package com.vmojing.mongodb.business.api;

import java.util.List;

import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Comment;
import com.vmojing.mongodb.domain.Weibo;

public interface ClueBusiness {
	/**
	 * save the clue to mongodb,or update if this object has already exist
	 * @param t
	 * @return
	 */
	boolean save(Clue c);
	boolean saveRetweetWeibo(Weibo weibo ,Clue c);
	boolean saveComment(Comment comment,Clue c);
	List<Clue> getAll();
}
