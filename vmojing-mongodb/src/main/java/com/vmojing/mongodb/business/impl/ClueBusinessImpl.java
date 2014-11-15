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
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.exception.VmojingMongoException;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBConvertor;
@Component
public class ClueBusinessImpl extends AbstractBusiness implements ClueBusiness{
	@Autowired
	@Qualifier("clueDao")
	BasicRepository<Clue> clueDao;
	@Autowired
	@Qualifier("clueConvertor")
	DBConvertor<Clue> clueConvertor;
	@Override
	public boolean save(Clue c) {
		// TODO Auto-generated method stub
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

}
