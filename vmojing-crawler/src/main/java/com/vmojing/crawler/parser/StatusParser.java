package com.vmojing.crawler.parser;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vmojing.dao.utils.AccessTokenAllocation;
import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
/**
 * 获取Status逻辑处理类
 * @author v11
 * @date 2014年10月11日
 * @Since 1.0
 */
public class StatusParser {
	private static final Logger log = LoggerFactory.getLogger(StatusParser.class);
	/**
	 * 通过新浪api获取微博详细信息
	 * @param wids 微博wids
	 * @return Statuses
	 */
	public List<Status> getStatuses(Set<String> wids) {
		// TODO Auto-generated method stub
		Timeline tm = new Timeline();
		tm.setToken(AccessTokenAllocation.getAccessToken());
		Iterator<String> iterator = wids.iterator();
		int i = 0;
		String idsStr = "";
		List<Status> allWeibos = new ArrayList<Status>();
		while (iterator.hasNext()) {
			String id = iterator.next();
			idsStr = idsStr + id + ",";
			i++;
			if (!iterator.hasNext() || i%50 == 0) { // 根据微博ID批量获取微博信息，最多不超过50个
				idsStr = idsStr.substring(0, idsStr.lastIndexOf(","));
				try {
					StatusWapper statusWapper = tm.getStatusByIds(idsStr, 0);
					List<Status> statuses = statusWapper.getStatuses();
					for (int j = 0; j < statuses.size(); j++) {
						allWeibos.add(statuses.get(j));
					}
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					log.error(e.toString());
				}
				idsStr = "";
			}
		}
		return allWeibos;
	}
}
