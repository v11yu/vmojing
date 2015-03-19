package com.vmojing.core.parser.api;

import java.util.Date;
import java.util.List;
import java.util.Set;





import com.vmojing.mongodb.domain.Comment;
import com.vmojing.mongodb.domain.Weibo;

import weibo4j.model.Status;

public interface WeiboParser {
	/**
	 * 通过新浪api获取微博详细信息，需直接更新的
	 * @param wids 待获取的微博id集合
	 * @param lastUpdateTime 系统最后更新时间 ,只获取该事件之后的微博
	 * @return weibo 微博列表:根据wids大小,or null if get failure
	 */
	public List<Weibo> getWeiboByWids(Set<String> wids,Date lastUpdateTime);
	/**
	 * 获取该微博的最新转发微博信息详细，需直接更新的
	 * @param wid 微博id
	 * @param lastUpdateRetweetTime 系统最后更新时间 ,只获取该事件之后的微博
	 * @return weibo 转发列表,最新2000,or null if get failure
	 */
	public List<Weibo> getRetweet(String wid,Date lastUpdateRetweetTime);
	/**
	 * 获取该博主发布的最新微博信息，需直接更新的
	 * @param uid 博主id
	 * @param lastUpdateWeiboTime 最后更新时间,只获取该事件之后的微博
	 * @return weibo 微博列表，最新200,or null if get failure
	 */
	public List<Weibo> getWeiboByUid (String uid,Date lastUpdateWeiboTime);
	/**
	 * 通过wid获取微博完整信息
	 * @param wid 微博id
	 * @return 完整微博信息,or null if get failure
	 */
	public Weibo getWeiboById(String wid);
	/**
	 * 通过mid获取微博转发mid列表
	 * @param mid 微博的mid
	 * @param page 转发列表页数,每页10个
	 * @return 转发微博的mid列表,每次10个
	 */
	public List<String> getWeiboMid(String mid,int page);
	/**
	 * 获取微博转发列表最大页数
	 * @param mid 微博mid
	 * @return 最大页数,-1表示获取失败
	 */
	int getPageCount(String mid);
	/**
	 * wid to mid
	 * @param wid 微博wid，数字串
	 * @return mid
	 */
	String wid2Mid(String wid);
	/**
	 * mid to wid
	 * @param mid 微博mid,字母和数字的串
	 * @return wid
	 */
	String mid2Wid(String mid);
}
