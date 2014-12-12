package com.vmojing.crawler.work;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.core.parser.api.WeiboParser;
import com.vmojing.crawler.CrawlerConfig;
import com.vmojing.crawler.fetcher.api.TopicFetcher;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;
import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.work.check.CheckStrategy;
import com.vmojing.crawler.work.push.PushStrategy;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;
@Component
@Scope("prototype")
public class SinaTopicInitializer extends AbstractWorker<Topic>{

	@Autowired
	TopicFetcher topicFetcher;
	@Autowired
	WeiboParser weiboParser;
	@Autowired
	TopicBusiness topicbusiness;
	@Autowired
	public SinaTopicInitializer(@Qualifier("initTopicQueue") BasicQueue<Topic> queue, 
			@Qualifier("noPush")PushStrategy pushStrategy,
			@Qualifier("noCheck") CheckStrategy checkStrategy) {
		super(CrawlerConfig.getNum("InitDelayTime")*minute,queue, pushStrategy,checkStrategy);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void work(Topic t) {
		// TODO Auto-generated method stub
		Date _begin = new Date(); // 用于程序计时
		Date beginTime = t.getBeginTime();
		if(t.getInitAtTime().getTime() != new Date(0).getTime()){
			beginTime = t.getInitAtTime();
		}
		String begin = getBeginTime(beginTime);
		String end = getEndTimeFromBeginTime(beginTime);
		getLogger().info("话题初始化线程开始:"+t.getTopicName()+" 从"+begin+"到"+end);
		Set<String> wids = topicFetcher.getIds(t.getTopicName(), MobileTopicFetcher.MaxPageNum, begin, end);
		List<Weibo> weibos = weiboParser.getWeiboByWids(wids, t.getInitAtTime());
		//更新initAtTime
		@SuppressWarnings("deprecation")
		Date newTime = new Date(beginTime.getYear(), beginTime.getMonth(), beginTime.getDate());
		Integer updateWeiboNums = 0;
		for(Weibo weibo :weibos){
			if(topicbusiness.saveWeibo(t,weibo)){
				updateWeiboNums++;
			}
			if(weibo.getCreatedAt().after(newTime))
				newTime = weibo.getCreatedAt();
		}
		t.setSum(t.getSum() + updateWeiboNums);
		t.setInitAtTime(newTime);
		topicbusiness.save(t);
		long time = new Date().getTime() - _begin.getTime();
		getLogger().info("话题初始化线程结束:"+t.getTopicName()+",用时："+time/1000+
				"秒,更新个数："+updateWeiboNums+"从"+begin+"到"+end);
	}
	String getBeginTime(Date date){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}
	@SuppressWarnings("deprecation")
	String getEndTimeFromBeginTime(Date beginTime){
		Date end = new Date(beginTime.getTime());
		end.setDate(beginTime.getDate()+1);
		System.out.println(beginTime);
		System.out.println(end);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(end);
	}

}
