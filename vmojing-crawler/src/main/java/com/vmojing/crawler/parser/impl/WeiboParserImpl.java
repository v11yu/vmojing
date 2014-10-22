package com.vmojing.crawler.parser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import com.vmojing.crawler.parser.WeiboParser;
import com.vmojing.mongodb.business.AccessTokenAllocation;
import com.vmojing.mongodb.domain.Weibo;


public class WeiboParserImpl implements WeiboParser {
	private static final Logger log = LoggerFactory
			.getLogger(WeiboParserImpl.class);

	@Override
	public List<Weibo> getWeibo(Set<String> wids, Date lastUpdateTime) {
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
			if (!iterator.hasNext() || i % 50 == 0) { // 根据微博ID批量获取微博信息，最多不超过50个
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
		return null;

	}

	@Override
	public List<Weibo> getRetweet(Long wid, Date lastUpdateRetweetTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Weibo> getWeibo(Long uid, Date lastUpdateWeiboTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
