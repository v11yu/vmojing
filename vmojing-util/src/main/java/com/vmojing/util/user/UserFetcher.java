package com.vmojing.util.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.core.parser.api.UserParser;
import com.vmojing.mongodb.domain.User;
import com.vmojing.util.UtilSpringConfigSingleton;
@Component
@Scope("prototype")
public class UserFetcher {
	@Autowired
	UserParser userParser;
	public User work(String uid){
		//userParser.
		User u = userParser.getUserById(uid);
		return u;
	}
	public static void main(String[] args) {
		String uid = "104335";
		UserFetcher uf = UtilSpringConfigSingleton.getContext().getBean(UserFetcher.class);
		uf.work(uid);
	}
}
