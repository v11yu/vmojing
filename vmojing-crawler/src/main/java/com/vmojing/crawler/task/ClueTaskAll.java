package com.vmojing.crawler.task;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmojing.core.parser.api.WeiboParser;
import com.vmojing.crawler.utils.CrawlerConfigSingleton;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
@Component
public class ClueTaskAll {
	Logger log = LoggerFactory.getLogger(ClueTaskAll.class);
	@Autowired
	@Qualifier("weiboDao")
	BasicRepository<Weibo> weiboDao;
	@Autowired
	WeiboParser wp;
	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void work(){
		DBCursor cursor = weiboDao.findByAll();

		int i = 0;
		int cnt = 0;
		Set<String> wids = new HashSet<String>();
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			String wid = (String) obj.get("_id");
			if(obj.get("text")!=null) continue;
			cnt++;
			wids.add(wid);
			System.out.println(wid);
			if(cnt == 50 || !cursor.hasNext()){
				List<Weibo> weibos = wp.getWeiboByWids(wids, new Date(0));
				wids = new HashSet<String>();
				cnt = 0;
				log.info("完成到第"+i+"个");
				try {
					weiboDao.saveAll(weibos);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sleep();
			}
			i++;
		}

	}
	public static void main(String[] args) {
	
		ClueTaskAll ct = (ClueTaskAll) CrawlerConfigSingleton.getBean("clueTaskAll");
		ct.work();
	}
}
