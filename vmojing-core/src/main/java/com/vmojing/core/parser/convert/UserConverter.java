package com.vmojing.core.parser.convert;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.mongodb.domain.User;
@Component
@Scope("prototype")
public class UserConverter extends Converter<User, weibo4j.model.User>{

	@Override
	protected void setManualField(weibo4j.model.User from, User to) {
		// TODO Auto-generated method stub
		to.setLastUpdateTime(new Date(0));
	}

}
