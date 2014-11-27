package com.vmojing.mongodb.business.api;

import java.util.List;

import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.domain.Weibo;

public interface BloggerBusiness {
	boolean save(Blogger c);
	List<Blogger> getAll();
	boolean saveWeibo(Weibo weibo,Blogger b);
	boolean saveFans(User fan,Blogger b);
	Blogger getById(String id);
	boolean delete(String id);
}
