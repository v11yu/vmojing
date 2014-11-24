package com.vmojing.mongodb.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmojing.mongodb.business.AbstractBusiness;
import com.vmojing.mongodb.business.api.BloggerBusiness;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBConvertor;
@Component
public class BloggerBusinessImpl extends AbstractBusiness implements BloggerBusiness {
	@Autowired
	@Qualifier("bloggerDao")
	BasicRepository<Blogger> bloggerDao;
	@Autowired
	@Qualifier("bloggerConvertor")
	DBConvertor<Blogger> bloggerConvertor;
	@Autowired
	@Qualifier("weiboDao")
	BasicRepository<Weibo> weiboDao;
	@Autowired
	@Qualifier("userDao")
	BasicRepository<User> userDao;
	@Override
	public boolean save(Blogger c) {
		// TODO Auto-generated method stub
		try {
			bloggerDao.saveAndUpdate(c);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error("保存blogger:"+c+" 失败");
		}
		return false;
	}

	@Override
	public List<Blogger> getAll() {
		// TODO Auto-generated method stub
		DBCursor cursor = null;
		List<Blogger> res = new ArrayList<Blogger>();
		try{
			cursor = bloggerDao.findByAll();
			while(cursor.hasNext()){
				DBObject obj = cursor.next();
				res.add(bloggerConvertor.convertToPojo(obj));
			}
		}catch(Exception e){
			getLogger().error("blogger getAll throw error " +e);
			res = null;
		}finally{
			if(cursor != null){
				cursor.close();
			}
		}
		return res;
	}

	@Override
	public boolean saveWeibo(Weibo weibo, Blogger b) {
		// TODO Auto-generated method stub
		Weibo w = weiboDao.findById(weibo.getId());
		if(w == null){
			try {
				weiboDao.saveAndUpdate(weibo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				getLogger().error("保存weibo"+weibo+"出错");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean saveFans(User fan, Blogger b) {
		// TODO Auto-generated method stub
		User u = userDao.findById(fan.getId());
		if(u == null){
			u = fan;
		}
		List<String> ls = u.getFriendsList();
		if(ls == null){
			ls = new ArrayList<String>();
		}
		// use to test system run
		if(ls.contains(b.getId())){
			getLogger().error("已经存在粉丝："+fan+"，在博主："+b.getUser().getName()+",这是系统异常");
			return false;
		}
		ls.add(b.getId());
		u.setFriendsList(ls);
		try {
			userDao.saveAndUpdate(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error("保存更新粉丝："+fan+",在博主："+b.getUser().getName()+",保存失败");
			return false;
		}
		return true;
	}

}
