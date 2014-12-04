package com.vmojing.mongodb.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.vmojing.mongodb.business.AbstractBusiness;
import com.vmojing.mongodb.business.api.UserBusiness;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBQuery;
@Component
public class UserBusinessImpl extends AbstractBusiness implements UserBusiness {
	@Autowired
	@Qualifier("userDao")
	BasicRepository<User> userDao;
	@Override
	public boolean existFans(String uid, String fid) {
		// TODO Auto-generated method stub
		User fan = userDao.findById(fid);
		if(fan == null){
			return false;
		}else{
			List<String> ls = fan.getFriendsList();
			for(String str:ls){
				if(str.equals(uid))
					return true;
			}
		}
		return false;
	}
	public List<User> filterUserList(List<User> all){
		List<User> newList = new ArrayList<User>();
		return newList;
	}
}
