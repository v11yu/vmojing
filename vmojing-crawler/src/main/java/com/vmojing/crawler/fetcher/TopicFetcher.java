package com.vmojing.crawler.fetcher;

import java.util.Set;
/**
 * 抓取话题功能的接口
 * @author v11
 * @date 2014年9月5日
 * @version 1.0
 */
public interface TopicFetcher {
	
	/**
	 * 获取话题wids列表
	 * <ol>
	 * <li>pageNum = 100</li>
	 * <li>end time = current time</li>
	 * </ol>
	 * @param word 关键字
	 * @return
	 */
	public Set<String> getIds(String word);
	/**
	 * 获取话题wids列表
	 * <ol>
	 * <li>单独设置开始时间可以</li>
	 * <li>单独设置结束时间就无法识别</li>
	 * </ol>
	 * @param word 关键字
	 * @param pageNum 爬取页数
	 * 
	 * @param starttime 开始时间
	 * <br />Example ： 20140806
	 * @param endtime 结束时间
	 * <br /> Example 20140901
	 * @return
	 */
	public Set<String> getIds(String word,Integer pageNum,String starttime,String endtime);
}
