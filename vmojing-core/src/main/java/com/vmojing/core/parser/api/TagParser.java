package com.vmojing.core.parser.api;

import java.util.List;



public interface TagParser {
	/**
	 * 根据标签(tag)名，获取标签对应用户数量
	 * @param tagName 标签名
	 * @return Tag
	 */
	com.vmojing.mongodb.domain.Tag getTag(String tagName);
	/**
	 * 根据uids获取用户标签List
	 * @param uids 要获取标签的用户ID。最大20，逗号分隔
	 * @return tag列表，失败返回Null
	 */
	List<com.vmojing.mongodb.domain.Tag> getTagsFromUsers(String uids);
}
