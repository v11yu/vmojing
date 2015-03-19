package com.vmojing.core.parser.convert;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import weibo4j.model.Status;

import com.vmojing.core.util.IdTransferUtil;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.domain.Weibo;
@Component
@Scope("prototype")
public class WeiboConverter extends Converter<Weibo, Status>{

	@Override
	protected void setManualField(Status from, Weibo to) {
		// TODO Auto-generated method stub
		UserConverter userConverter = new UserConverter();
		if(from.getUser() != null){
			User u = userConverter.convert(from.getUser());
			to.setUser(u);
		}
		to.setLastUpdateTime(new Date(0));
		to.setRetweetWeiboId(from.getRetweetedStatus() == null?null:from.getRetweetedStatus().getId());
		to.setTopicIds(null);
		to.setMid(IdTransferUtil.id2Mid(from.getId()));
		if(from.getSource() == null){
			to.setSource(null);
		}else{
			to.setSource(from.getSource().toString());
		}
	}

}
