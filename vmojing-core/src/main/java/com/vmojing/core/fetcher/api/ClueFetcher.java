package com.vmojing.core.fetcher.api;

import java.util.List;

import com.vmojing.mongodb.domain.Weibo;

/**
 * 针对一条微博，获取转发列表
 * 
 * @author v11
 */
public interface ClueFetcher {
	/**
	 * 通过手机端，解析网页获取转发列表
	 * @param page 转发列表页码
	 * @param mid 微博mid
	 * @return 转发微博列表
	 */
	List<Weibo> getRepost(int page,String mid);
	/**
	 * 获取mid列表
	 * @param weibos 转发微博列表
	 * @return mid列表
	 */
	List<String> getRepostMid(int page, String mid);
	/**
	 * 获取微博转发列表最大页数
	 * @param mid 微博mid
	 * @return 最大页数,-1表示获取失败
	 */
	int getPageCount(String mid);
}
