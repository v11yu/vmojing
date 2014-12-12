package com.vmojing.crawler.work;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.core.parser.api.UserParser;
import com.vmojing.core.parser.api.WeiboParser;
import com.vmojing.crawler.CrawlerConfig;
import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.work.check.CheckStrategy;
import com.vmojing.crawler.work.interval.IntervalStrategy;
import com.vmojing.crawler.work.push.PushStrategy;
import com.vmojing.mongodb.business.api.BloggerBusiness;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.domain.Weibo;
@Component
@Scope("prototype")
public class SinaBloggerWorker extends AbstractWorker<Blogger>{
	
	@Autowired
	WeiboParser weiboParser;
	@Autowired
	UserParser userParser;
	@Autowired
	BloggerBusiness bloggerBusiness;
	@Autowired
	@Qualifier("simpleInterval")
	IntervalStrategy simpleInterval;
	@Autowired
	public SinaBloggerWorker(BasicQueue<Blogger> queue, 
			@Qualifier("noPush") PushStrategy pushStrategy,
			@Qualifier("workCheck") CheckStrategy checkStrategy) {
		super(queue, pushStrategy,checkStrategy);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void work(Blogger t) {
		// TODO Auto-generated method stub
		Date begin = new Date();
		Date update = t.getLastUpdateWeiboTime();
		getLogger().info("博主抓取线程开始："+t.getUser().getName());
		List<Weibo> weibos = weiboParser.getWeiboByUid(t.getId(), t.getLastUpdateWeiboTime());
		List<User> fans = userParser.getNewFans(t.getId());
		Integer updateFanNums = -1;
		Integer updateWeiboNums = -1;
		//---weibo doing
		if (weibos != null) {
			updateWeiboNums = weibos.size();
			for (Weibo weibo : weibos) {
				bloggerBusiness.saveWeibo(weibo, t);
				if (weibo.getCreatedAt().after(update)) {
					update = weibo.getCreatedAt();
				}
			}
			t.setLastUpdateWeiboTime(update);
		}
		//---fans doing
		if (fans != null) {
			updateFanNums = 0;
			for (User u : fans) {
				if (bloggerBusiness.saveFans(u, t)) {
					updateFanNums++;
				}
			}
			t.setLastUpdateFansTime(new Date());
		}
		t.setLastTime(new Date());
		updateInterval(t,updateFanNums,updateWeiboNums);
		bloggerBusiness.save(t);
		long useTime = new Date().getTime() - begin.getTime();
		getLogger().info("博主抓取线程结束："+t.getUser().getName()+",用时："
		+useTime/1000+"秒,更新粉丝数："+updateFanNums+",更新微博数："+updateWeiboNums);
	}
	private void updateInterval(Blogger t,Integer updateFanNums,Integer updateWeiboNums){
		int t1 = simpleInterval.getInterval(t.getUpdateFrequency(), updateFanNums,
				CrawlerConfig.getNum("blogger_fan_left_threshold"),CrawlerConfig.getNum("blogger_fan_right_threshold"), CrawlerConfig.getNum("interval"));
		int t2 = simpleInterval.getInterval(t.getUpdateFrequency(), updateWeiboNums,
				CrawlerConfig.getNum("blogger_weibo_left_threshold"),CrawlerConfig.getNum("blogger_weibo_right_threshold"),  CrawlerConfig.getNum("interval"));
		t.setUpdateFrequency(Math.min(t1, t2));
	}
}
