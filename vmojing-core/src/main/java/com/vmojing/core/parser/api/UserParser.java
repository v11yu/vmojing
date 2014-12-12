package com.vmojing.core.parser.api;

import java.util.Date;
import java.util.List;

import com.vmojing.mongodb.domain.User;

public interface UserParser {
	/**
	 * 获取用户未更新粉丝信息,都是需要直接更新的粉丝信息
	 * @param uid 博主id
	 * @return 最新的粉丝列表:最多2000,or null if get failure
	 */
	public List<User> getNewFans(String uid);
	/**
	 * 获取用户所有的能获取的粉丝信息
	 * @param uid 用户id
	 * @return 粉丝列表:最多2000,or null if get failure
	 */
	public List<User> getAllFans(String uid);
	/**
	 * 获取用户Id，通过用户名
	 * @param name 用户名
	 * @return 用户,or null if fail
	 */
	public User getUserByName(String name);
}
