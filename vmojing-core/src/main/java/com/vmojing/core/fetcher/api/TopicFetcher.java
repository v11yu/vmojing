package com.vmojing.core.fetcher.api;

import java.util.Set;
/**
 * 抓取话题功能的接口
 * @author v11
 * @date 2014年9月5日
 */
public interface TopicFetcher {
	/**
	 * 获取最近话题wids列表,最多只有1000条
	 * <ol>
	 * <li>pageNum = 100</li>
	 * <li>end time = current time</li>
	 * </ol>
	 * @param words 关键字,用半角逗号分隔，例如："A,B"
	 * @return
	 */
	public Set<String> getIds(String words);
	/**
	 * 获取话题wids列表,最多只有1000条
	 * <ol>
	 * <li>单独设置开始时间可以</li>
	 * <li>单独设置结束时间就无法识别</li>
	 * </ol>
	 * @param words 关键字,用半角逗号分隔，例如："A,B"
	 * @param pageNum 爬取页数
	 * 
	 * @param starttime 开始时间
	 * <br />Example ： 20140806
	 * @param endtime 结束时间
	 * <br /> Example 20140901
	 * @return
	 */
	public Set<String> getIds(String words,Integer pageNum,String starttime,String endtime);

}
