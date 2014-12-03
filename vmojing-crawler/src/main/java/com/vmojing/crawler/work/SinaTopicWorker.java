package com.vmojing.crawler.work;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.fetcher.api.Loginer;
import com.vmojing.crawler.fetcher.api.TopicFetcher;
import com.vmojing.crawler.parser.api.WeiboParser;
import com.vmojing.crawler.queue.BasicQueue;
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
	public SinaTopicWorker(BasicQueue<Topic> queue, 
			@Qualifier("noPush")PushStrategy pushStrategy) {
		super(queue, pushStrategy);
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
		Set<String> wids = topicFetcher.getIds(t.getTopicName());
		List<Weibo> weibos = weiboParser.getWeiboByWids(wids, t.getLastUpdateTime());
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
		topicbusiness.save(t);
		long time = begin.getTime() - new Date().getTime();
		getLogger().info("话题获取线程结束:"+t.getTopicName()+",用时："+time/1000+
				"秒,更新个数："+updateWeiboNums);
	}

}
