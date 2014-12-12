package com.vmojing.crawler.client;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.CrawlerConfig;
import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.work.*;
import com.vmojing.mongodb.business.api.TopicBusiness;
import com.vmojing.mongodb.domain.Topic;


@Component
@Scope("prototype")
public class TopicInitClient extends Client{
	@Autowired
	ApplicationContext context;
	@Autowired
	@Qualifier("initTopicQueue")
	private BasicQueue<Topic> initTopicQueue;
	
	final Integer maxInitTopic = CrawlerConfig.getNum("MaxInitTopic");

	ThreadPoolTaskExecutor taskExecutor;
	@Override
	void work() {
		// TODO Auto-generated method stub
		for(int i=0;i<Math.min(maxInitTopic,initTopicQueue.size());i++){
			SinaTopicInitializer c = context.getBean(SinaTopicInitializer.class);
			taskExecutor.execute(c);
		}
		waitThread(taskExecutor);
	}

	@Override
	void initialize() {
		// TODO Auto-generated method stub
		TopicBusiness topicBusiness = context.getBean(TopicBusiness.class);
		taskExecutor = context.getBean(ThreadPoolTaskExecutor.class);
		List<Topic> ls = topicBusiness.getAll();
		fillQueue(initTopicQueue,ls);
	}

	@Override
	void after() {
		// TODO Auto-generated method stub
		taskExecutor.shutdown();
	}

	@Override
	<T> void fillQueue(BasicQueue<T> queue, List<T> ls) {
		// TODO Auto-generated method stub
		while(!queue.isEmpty()){
			getLogger().error("队列非空");
			queue.pop();
		}
		int count = 0;
		for(T tmp:ls){
			Topic t = (Topic) tmp;
			if(t.getInitAtTime().before(t.getCreateAtTime()) && t.getOperateStatus() == 0){
				queue.push(tmp);
				count++;
			}
		}
		getLogger().info("装配广搜队列："+count);
	}
}
