package com.vmojing.crawler.work;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.CrawlerConfig;
import com.vmojing.crawler.fetcher.api.Loginer;
import com.vmojing.crawler.fetcher.api.TopicFetcher;
import com.vmojing.core.parser.api.WeiboParser;
import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.work.check.CheckStrategy;
import com.vmojing.crawler.work.interval.IntervalStrategy;
import com.vmojing.crawler.work.push.PushStrategy;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.domain.Weibo;
@Component
@Scope("prototype")
public class SinaTopicWorker extends AbstractWorker<Topic> {
	@Autowired
	TopicFetcher topicFetcher;
	@Autowired
	WeiboParser weiboParser;
	@Autowired
	TopicBusiness topicbusiness;
	@Autowired
	@Qualifier("simpleInterval")
	IntervalStrategy simpleInterval;
	@Autowired
	public SinaTopicWorker(@Qualifier("topicQueue") BasicQueue<Topic> queue, 
			@Qualifier("noPush")PushStrategy pushStrategy,
			@Qualifier("workCheck") CheckStrategy checkStrategy) {
		super(queue, pushStrategy,checkStrategy);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void work(Topic t) {
		// TODO Auto-generated method stub
		Date begin = new Date();
		Date lastTime = t.getLastUpdateTime();
		getLogger().info("话题获取线程开始:"+t.getTopicName());
		if(topicFetcher == null){
			getLogger().info("话题获取线程异常:"+t.getTopicName()+",httpclient = null,结束等待下次抓取");
			return ;
		}
		Set<String> wids = new HashSet<String>();

		//---doing topic fetch and save
		wids.addAll(topicFetcher.getIds(t.getTopicName(),5,"",""));
		getLogger().info("话题:"+t.getTopicName()+"获取wids："+wids.size());
		List<Weibo> weibos = weiboParser.getWeiboByWids(wids, t.getLastUpdateTime());
		getLogger().info("话题:"+t.getTopicName()+"获取需更新weibos："+weibos.size());
		Integer updateWeiboNums = 0;
		for(Weibo weibo :weibos){
			if(topicbusiness.saveWeibo(t,weibo)){
				updateWeiboNums++;
			}
			if(weibo.getCreatedAt().after(lastTime))
				lastTime = weibo.getCreatedAt();
		}
		t.setSum(t.getSum() + updateWeiboNums);
		t.setLastUpdateTime(lastTime);
		t.setLastTime(new Date());
		t.setUpdateFrequency(simpleInterval.getInterval(t.getUpdateFrequency(), updateWeiboNums,
				CrawlerConfig.getNum("topic_left_threshold"),CrawlerConfig.getNum("topic_right_threshold"), CrawlerConfig.getNum("interval")));
		topicbusiness.save(t);
		long time = new Date().getTime() - begin.getTime();
		getLogger().info("话题获取线程结束:"+t.getTopicName()+",用时："+time/1000+
				"秒,更新个数："+updateWeiboNums);
	}

}
