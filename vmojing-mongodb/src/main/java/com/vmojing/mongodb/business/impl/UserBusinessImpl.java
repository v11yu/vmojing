package com.vmojing.mongodb.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.vmojing.mongodb.business.api.UserBusiness;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.repository.BasicRepository;
@Component
public class UserBusinessImpl implements UserBusiness{
	@Autowired
	@Qualifier("userDao")
	BasicRepository<User> userDao;
	@Override
	public boolean exitFans(String uid, String fid) {
		// TODO Auto-generated method stub
		return false;
	}
}
