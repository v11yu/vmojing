package com.vmojing.crawler.work;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DBCursor;
import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.DBQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerRootConfiguration.class})
public class ClueTaskTest {
	@Autowired
	@Qualifier("weiboDao")
	BasicRepository<Weibo> weiboDao;
	@Test
	public void testNum(){
		DBQuery query = new DBQuery();
		query.neOperation("text", null);
		DBCursor cursor = weiboDao.findQuery(query);
		System.out.println("有内容的记录:"+cursor.size());
		cursor.close();
		query = new DBQuery();
		query.equalsOperation("text", null);
		DBCursor cursor2 = weiboDao.findQuery(query);
		System.out.println("没有内容的记录:"+cursor2.size());
		cursor2.close();
		
	}
}
