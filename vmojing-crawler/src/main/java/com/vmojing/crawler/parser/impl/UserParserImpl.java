package com.vmojing.crawler.parser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import weibo4j.Friendships;
import weibo4j.Users;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

import com.vmojing.crawler.parser.UserParser;
import com.vmojing.crawler.parser.WeiboExceptionHandle;
import com.vmojing.crawler.parser.convert.UserConverter;
import com.vmojing.mongodb.business.AccessTokenAllocation;
import com.vmojing.mongodb.business.api.UserBusiness;
import com.vmojing.mongodb.domain.User;
@Component
public class UserParserImpl implements UserParser{
	private static final Logger log = LoggerFactory
			.getLogger(UserParserImpl.class);
	private static final Integer MaxFan = 200;
	private static final Integer MaxPage = 10;
	private static final Integer FindNum = 10;//每次取前多少段，去判重
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserBusiness userBusiness;
	private Users um;
	private Friendships fm ;
	@PostConstruct
	private void initialize(){
		um = new Users();
		um.setToken(AccessTokenAllocation.getAccessToken());
		fm = new Friendships();
		fm.setToken(AccessTokenAllocation.getAccessToken());
		log.debug("初始化accessToken");
	}
	/**
	 * 判断这个segment的开头是否都已经在数据库了
	 * @param uid blogger Id
	 * @param us 粉丝List
	 * @return
	 */
	private boolean checkFansListExit(String uid,List<User> us){
		for(int i=0;i<MaxFan&&i<us.size();i++){
			if(!userBusiness.exitFans(uid, us.get(i).getId()))
				return true;
		}
		return false;
	}
	/**
	 * 转换函数
	 * @param us weibo4j.model.User
	 * @return
	 */
	private List<User> tranferUser(List<weibo4j.model.User> us){
		List<User> res = new ArrayList<User>();
		for(weibo4j.model.User u : us){
			res.add(userConverter.convert(u));
		}
		return res;
	}
	/**
	 * 添加新粉丝list
	 * @param uid 博主id
	 * @param res 最终更新的粉丝list
	 * @param pre 待添加的粉丝list
	 */
	private void addSegmentToResLst(String uid, List<User> res, List<User> pre) {
		for (User u : pre) {
			int flag = 0;
			if (flag > MaxFan)
				break;
			if (userBusiness.exitFans(uid, u.getId())) {
				flag++;
			} else {
				flag = 0;
				res.add(u);
			}
		}
	}
	@Override
	public List<User> getFans(String uid) {
		// TODO Auto-generated method stub
		List<User> res = new ArrayList<User>();
		List<User> pre = new ArrayList<User>();
		for(int i=0;i<MaxPage;i++){
			try {
				UserWapper uw = fm.getFollowersById(uid, MaxFan, MaxFan*i);
				List<User> userSegment = tranferUser(uw.getUsers());
				if(checkFansListExit(uid,userSegment)){
					res.addAll(pre);
					pre = userSegment;
				}else{
					break;
				}
			} catch (WeiboException e) {
				// TODO Auto-generated catch block
				log.error(WeiboExceptionHandle.getErrorString(e, "获取用户"+uid+"粉丝出错"));
			}
		}
		// check List of pre
		addSegmentToResLst(uid,res,pre);
		return res;
	}

	@Override
	public User getIdByName(String name) {
		// TODO Auto-generated method stub
		weibo4j.model.User u = null;
		try {
			u = um.showUserByName(name);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			log.error(WeiboExceptionHandle.getErrorString(e, "获取用户"+name+"错误"));
			return null;
		}
		return userConverter.convert(u);
	}

}
