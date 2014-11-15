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
	@Override
	public boolean save(Blogger c) {
		// TODO Auto-generated method stub
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

}
