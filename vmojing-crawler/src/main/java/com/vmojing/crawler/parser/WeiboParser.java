package com.vmojing.crawler.parser;

import java.util.Date;
import java.util.List;
import java.util.Set;



import com.vmojing.mongodb.domain.Comment;
import com.vmojing.mongodb.domain.Weibo;

import weibo4j.model.Status;

public interface WeiboParser {
	/**
	 * 通过新浪api获取微博详细信息
	 * @param wids 待获取的微博id集合
	 * @param lastUpdateTime 系统最后更新时间
	 * @return weibo 微博列表
	 */
	public List<Weibo> getWeibo(Set<String> wids,Date lastUpdateTime);
	/**
	 * 获取该微博的最新转发微博信息详细
	 * @param wid 微博id
	 * @param lastUpdateRetweetTime 系统最后更新时间
	  * @return weibo 转发列表
	 */
	public List<Weibo> getRetweet(Long wid,Date lastUpdateRetweetTime);
	/**
	 * 获取该博主发布的最新微博信息
	 * @param uid 博主id
	 * @param lastUpdateWeiboTime 最后更新时间
	 * @return weibo 微博列表
	 */
	public List<Weibo> getWeibo (Long uid,Date lastUpdateWeiboTime);
}
