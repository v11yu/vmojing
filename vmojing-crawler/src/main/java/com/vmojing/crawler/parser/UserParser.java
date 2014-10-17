package com.vmojing.crawler.parser;

import java.util.Date;
import java.util.List;

import com.vmojing.mongodb.domain.User;

public interface UserParser {
	/**
	 * 获取用户粉丝信息
	 * @param uid 博主id
	 * @param lastUpdateTime 最后
	 * @return 最新的粉丝列表
	 */
	public List<User> getFans(Long uid,Date lastUpdateFansTime);
}
