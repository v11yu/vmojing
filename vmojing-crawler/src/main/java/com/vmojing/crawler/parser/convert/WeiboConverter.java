package com.vmojing.crawler.parser.convert;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import weibo4j.model.Status;

import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.domain.Weibo;
@Component
public class WeiboConverter extends Converter<Weibo, Status>{

	@Override
	protected void setManualField(Status from, Weibo to) {
		// TODO Auto-generated method stub
		UserConverter userConverter = new UserConverter();
		User u = userConverter.convert(from.getUser());
		to.setUser(u);
		to.setLastUpdateTime(new Date(0));
		to.setRetweetWeiboId(from.getRetweetedStatus() == null?null:from.getRetweetedStatus().getId());
		to.setTopicIds(null);
		
	}

}
