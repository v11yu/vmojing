package com.vmojing.core.parser.sina.api;

import java.util.Date;
import java.util.List;

import com.vmojing.mongodb.domain.User;

public interface UserParser {
	/**
	 * 获取用户粉丝信息,都是需要直接更新的粉丝信息
	 * @param uid 博主id
	 * @return 最新的粉丝列表
	 */
	public List<User> getFans(String uid);
	/**
	 * 获取用户Id，通过用户名
	 * @param name 用户名
	 * @return 用户id,or null if fail 
	 */
	public User getUserByName(String name);
}