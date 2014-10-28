package com.vmojing.crawler.parser.convert;

import org.springframework.stereotype.Component;

import weibo4j.model.Status;

import com.vmojing.crawler.parser.Converter;
import com.vmojing.mongodb.domain.Weibo;
@Component
public class WeiboConverter implements Converter<Weibo, Status>{

	@Override
	public Weibo convert(Status from) {
		// TODO Auto-generated method stub
//		Weibo weibo = new Weibo();
//		weibo.setAttitudeCount(from.getAttitudeCount());
//		weibo.setBmiddlePicture(from.getBmiddlePic());
//		weibo.setCommentCount(from.getCommentsCount());
//		weibo.setCreateAtTime(from.getCreatedAt());
//		weibo.setId(Long.valueOf(from.getId()));
//		weibo.setLastUpdateTime(lastUpdateTime);
//		weibo.setLatitude(from.getLatitude());
//		weibo.setLongitude(from.getLongitude());
//		weibo.setMonitoredTime(monitoredTime);
//		weibo.setOriginalPicture(from.getOriginalPic());
		
		return null;
	}

}
