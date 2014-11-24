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
import com.vmojing.mongodb.business.api.ClueBusiness;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Comment;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.exception.VmojingMongoException;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBConvertor;
import com.vmojing.mongodb.repository.DBQuery;
@Component
public class ClueBusinessImpl extends AbstractBusiness implements ClueBusiness{
	@Autowired
	@Qualifier("clueDao")
	BasicRepository<Clue> clueDao;
	@Autowired
	@Qualifier("clueConvertor")
	DBConvertor<Clue> clueConvertor;
	@Autowired
	@Qualifier("weiboDao")
	BasicRepository<Weibo> weiboDao;
	@Autowired
	@Qualifier("commentDao")
	BasicRepository<Comment> commentDao;
	@Override
	public boolean save(Clue c) {
		// TODO Auto-generated method stub
		try {
			clueDao.saveAndUpdate(c);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error("保存 Clue:"+c+"失败");
		}
		return false;
	}

	@Override
	public List<Clue> getAll() {
		// TODO Auto-generated method stub
		DBCursor cursor = null;
		List<Clue> res = new ArrayList<Clue>();
		try{
			cursor = clueDao.findByAll();
			while(cursor.hasNext()){
				DBObject obj = cursor.next();
				res.add(clueConvertor.convertToPojo(obj));
			}
		}catch(Exception e){
			getLogger().error("clue getAll throw error " +e);
			//throw new VmojingMongoException("clue getAll throw error",e);
			return null;
		}finally{
			if(cursor != null){
				cursor.close();
			}
		}
		return res;
	}

	@Override
	public boolean saveRetweetWeibo(Weibo weibo, Clue c) {
		// TODO Auto-generated method stub
		Weibo w = weiboDao.findById(weibo.getId());
		if(w == null){
			w = weibo;
		}else{
			getLogger().error("抓取Clue转发微博，出现已存在微博!"+weibo+" 线索："+c);
			w.setRetweetWeiboId(c.getId());
		}
		try {
			weiboDao.saveAndUpdate(w);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	@Override
	public boolean saveComment(Comment comment, Clue c) {
		// TODO Auto-generated method stub
		Comment cm = commentDao.findById(comment.getId());
		if(cm == null){
			cm = comment;
		}else{
			getLogger().error("抓取Clue的评论时，出现已存在评论异常!"+comment+" 线索："+c);
		}
		try{
			commentDao.saveAndUpdate(cm);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
}
