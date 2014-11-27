package com.vmojing.core.parser.sina.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import com.vmojing.core.parser.sina.WeiboExceptionHandle;
import com.vmojing.core.parser.sina.api.WeiboParser;
import com.vmojing.core.parser.sina.convert.Converter;
import com.vmojing.core.parser.sina.convert.WeiboConverter;
import com.vmojing.mongodb.business.AccessTokenAllocation;
import com.vmojing.mongodb.domain.Weibo;

@Component
@Scope("prototype")
public class WeiboParserImpl implements WeiboParser {
	private static final Logger log = LoggerFactory
			.getLogger(WeiboParserImpl.class);
	private static int MaxRetWeetPage = 11;
	private static int MaxRetWeetSize = 200;
	private static int MaxUserWeiboSize = 200;
	private static int MaxWidsSize = 50;
	@Autowired
	private WeiboConverter weiboConverter;
	private Timeline tm ;
	
	@PostConstruct
	private void initialize(){
		tm = new Timeline();
		tm.setToken(AccessTokenAllocation.getAccessToken());
	}
	@Override
	public List<Weibo> getWeiboByWids(Set<String> wids, Date lastUpdateTime) {
		// TODO Auto-generated method stub
		Iterator<String> iterator = wids.iterator();
		int i = 0;
		String idsStr = "";
		List<Weibo> allWeibos = new ArrayList<Weibo>();
		while (iterator.hasNext()) {
			String id = iterator.next();
			idsStr = idsStr + id + ",";
			i++;
			if (!iterator.hasNext() || i % MaxWidsSize == 0) { // 根据微博ID批量获取微博信息，最多不超过50个
				idsStr = idsStr.substring(0, idsStr.lastIndexOf(","));
				try {
					List<Weibo> weibos = getWeiboAfterTime(tm.getStatusByIds(idsStr, 0), lastUpdateTime);
					if(null == weibos){
						log.error("微博列表"+idsStr +" getWeiboByWids出错，StatusWapper is null");
						continue;
					}
					allWeibos.addAll(getWeiboAfterTime(tm.getStatusByIds(idsStr, 0), lastUpdateTime));
					
				} catch (WeiboException e) {
					// TODO Auto-generated catch block
					log.error(WeiboExceptionHandle.getErrorString(e, ""));
					return null;
				}
				idsStr = "";
			}
		}
		return allWeibos;
	}
	@Override
	public List<Weibo> getRetweet(String wid, Date lastUpdateRetweetTime) {
		// TODO Auto-generated method stub
		StatusWapper sw = null;
		List<Weibo> res = new ArrayList<Weibo>();
		for (int i = 1; i < MaxRetWeetPage; i++) {
			try {
				sw = tm.getRepostTimeline(wid, MaxRetWeetSize, i);
				List<Weibo> weibos = getWeiboAfterTime(sw,lastUpdateRetweetTime);
				if(null == weibos){
					log.error("微博"+wid +" getRetweet出错，StatusWapper is null");
					continue;
				}
				if(0 == weibos.size()){
					log.info("微博"+wid +" 转发列表已采集完成，采集至第"+(i-1));
					break;
				}
				res.addAll(weibos);
			} catch (WeiboException e) {
				// TODO Auto-generated catch block
				log.error(WeiboExceptionHandle.getErrorString(e, ""));
				return null;
			}
		}
		return res;
	}

	@Override
	public List<Weibo> getWeiboByUid(String uid, Date lastUpdateWeiboTime) {
		// TODO Auto-generated method stub
		StatusWapper sw = null;
		try {
			sw = tm.getUserTimelineBatchByUids(uid, MaxUserWeiboSize, 1); // max page
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			log.error(WeiboExceptionHandle.getErrorString(e, ""));
			return null;
		}
		return getWeiboAfterTime(sw,lastUpdateWeiboTime);
		
	}

	private List<Weibo> getWeiboAfterTime(StatusWapper sw,Date tim){
		List<Weibo> weibos = new ArrayList<Weibo>();
		if(sw == null || sw.getStatuses() == null) return weibos; 
		for(Status st: sw.getStatuses()){
			if(st.getCreatedAt().after(tim)){
				weibos.add(weiboConverter.convert(st));
			}
		}
		return weibos;
	}
	@Override
	public Weibo getWeiboById(String wid) {
		// TODO Auto-generated method stub
		try {
			List<Weibo> weibos = getWeiboAfterTime(tm.getStatusByIds(wid, 0),new Date(0));
			if(weibos.size() == 1){
				return weibos.get(0);
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			log.error(WeiboExceptionHandle.getErrorString(e, ""));
		} catch (Exception e){
			log.error(""+e);
		}
		return null;
	}

}
