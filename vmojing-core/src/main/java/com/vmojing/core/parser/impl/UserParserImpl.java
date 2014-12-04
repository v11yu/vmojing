package com.vmojing.core.parser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import weibo4j.Friendships;
import weibo4j.Users;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

import com.vmojing.core.parser.WeiboExceptionHandle;
import com.vmojing.core.parser.api.UserParser;
import com.vmojing.core.parser.convert.UserConverter;
import com.vmojing.mongodb.business.AccessTokenAllocation;
import com.vmojing.mongodb.business.api.UserBusiness;
import com.vmojing.mongodb.domain.User;
@Component
@Scope("prototype")
public class UserParserImpl implements UserParser{
	private static final Logger log = LoggerFactory
			.getLogger(UserParserImpl.class);
	private static final Integer MaxFan = 200; // 每次取最大粉丝数来判重
	private static final Integer MaxPage = 10;
	private static final Integer FindNum = 20;//每次取前多少段，去判重
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
		for(int i=0;i<FindNum&&i<us.size();i++){
			if(!userBusiness.existFans(uid, us.get(i).getId()))
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
	 * 添加剩余的新粉丝list To res
	 * @param uid 博主id
	 * @param res 最终更新的粉丝list
	 * @param pre 待添加的粉丝list
	 */
	private void addSegmentToResLst(String uid, List<User> res, List<User> pre) {
		for (User u : pre) {
			int flag = 0;
			if (flag > FindNum)
				break;
			if (userBusiness.existFans(uid, u.getId())) {
				flag++;
			} else {
				flag = 0;
				res.add(u);
			}
		}
	}
	@Override
	public List<User> getNewFans(String uid) {
		// TODO Auto-generated method stub
		List<User> res = new ArrayList<User>();
		List<User> pre = new ArrayList<User>();
		for(int i=0;i<MaxPage;i++){
			try {
				UserWapper uw = fm.getFollowersById(uid, MaxFan, MaxFan*i);
				List<User> userSegment = tranferUser(uw.getUsers());
				if(checkFansListExit(uid,userSegment)){
					res.addAll(pre);//next segment is ok --> pre can add
					pre = userSegment;
				}else{
					break;
				}
			} catch (WeiboException e) {
				// TODO Auto-generated catch block
				log.error(WeiboExceptionHandle.getErrorString(e, "获取用户"+uid+"粉丝出错"));
				return null;
			}
		}
		// check List of pre
		addSegmentToResLst(uid,res,pre);
		return res;
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		weibo4j.model.User u = null;
		try {
			u = um.showUserByName(name);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			log.error(WeiboExceptionHandle.getErrorString(e, "获取最新用户"+name+"错误"));
			return null;
		}
		return userConverter.convert(u);
	}
	@Override
	public List<User> getAllFans(String uid) {
		// TODO Auto-generated method stub
		List<User> res = new ArrayList<User>();
		for(int i=0;i<MaxPage;i++){
			try {
				UserWapper uw = fm.getFollowersById(uid, MaxFan, MaxFan*i);
				if(uw == null || uw.getUsers() == null || uw.getUsers().size() == 0){
					break ;
				}
				List<User> userSegment = tranferUser(uw.getUsers());
				res.addAll(userSegment);
			} catch (WeiboException e) {
				// TODO Auto-generated catch block
				log.error(WeiboExceptionHandle.getErrorString(e, "获取全部用户"+uid+"粉丝出错"));
				return null;
			}
		}
		return res;
	}

}
