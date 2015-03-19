package com.vmojing.util.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.vmojing.core.file.OutputTool;
import com.vmojing.core.file.imp.OutputToolImpl;
import com.vmojing.mongodb.domain.Tag;
import com.vmojing.mongodb.repository.BasicRepository;
/**
 * 排序输出标签
 * 
 * @author v11
 */
class TagWeightCmp implements Comparator<Tag>{

	@Override
	public int compare(Tag o1, Tag o2) {
		// TODO Auto-generated method stub
		int n1 = Integer.parseInt(o1.getWeight());
		int n2 = Integer.parseInt(o2.getWeight());
		if(n1 < n2) return 1;
		else return -1;
	}
}
@Component
public class TagExport {
	@Autowired
	@Qualifier("tagDao")
	BasicRepository<Tag> tagDao;
	OutputTool ot = new OutputToolImpl();
	List<Tag> getListFromDb(){
		DBCursor cursor = tagDao.findByAll();
		
		return tagDao.dbobj2Entity(cursor);
	}
	List<String> tag2str(List<Tag> ts){
		List<String> ls = new ArrayList<String>();
		for(Tag t : ts){
			String str = "标签:"+t.getValue()+" 人数:"+t.getWeight();
			ls.add(str);
		}
		return ls;
	}
	public void work(){
		List<Tag> ts = getListFromDb();
		ts.sort(new TagWeightCmp());
		List<String> ls = tag2str(ts);
		System.out.println(ls.get(0));
		ot.newAndOutput("d://tags.txt", ls);
	}
}
