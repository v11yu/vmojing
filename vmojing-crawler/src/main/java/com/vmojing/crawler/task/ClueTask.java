package com.vmojing.crawler.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.vmojing.core.fetcher.api.ClueFetcher;
import com.vmojing.core.fetcher.impl.MobileSinaLoginer;
import com.vmojing.core.parser.api.WeiboParser;
import com.vmojing.core.util.CoreConfigSingleton;
import com.vmojing.crawler.utils.CrawlerConfigSingleton;
import com.vmojing.mongodb.domain.Tag;
import com.vmojing.mongodb.domain.Weibo;
import com.vmojing.mongodb.repository.BasicRepository;
@Component
public class ClueTask {
	@Autowired
	@Qualifier("weiboDao")
	BasicRepository<Weibo> weiboDao;
	@Autowired
	WeiboParser wp;
	private void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void work(){
		String mid = "C6Fuyv2HS";

		int maxNum = wp.getPageCount(mid);
		for (int i = 1; i <= maxNum; i++) {
			List<String> res = wp.getWeiboMid(mid, i);
			System.out.println(res);
			sleep();
			for(String str:res){
				Weibo w = new Weibo();
				w.setMid(str);
				w.setId(wp.mid2Wid(str));
				try {
					weiboDao.saveAndUpdate(w);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		ClueTask ct = (ClueTask) CrawlerConfigSingleton.getBean("clueTask");
		ct.work();
	}
}
