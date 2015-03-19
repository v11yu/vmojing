package com.vmojing.crawler.client;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.crawler.queue.*;
import com.vmojing.crawler.utils.CrawlerConfig;
import com.vmojing.crawler.work.SinaBloggerWorker;
import com.vmojing.crawler.work.SinaClueWorker;
import com.vmojing.crawler.work.SinaTopicWorker;
import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.business.api.*;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;

@Component
@Scope("prototype")
public class CrawlerClient extends Client{
	@Autowired
	ApplicationContext context;

	ThreadPoolTaskExecutor taskExecutor;
	/*
	 * multiple-thread queue
	 */
	@Autowired
	@Qualifier("topicQueue")
	private BasicQueue<Topic> topicQueue;
	@Autowired
	@Qualifier("bloggerQueue")
	private BasicQueue<Blogger> bloggerQueue;
	@Autowired
	@Qualifier("clueQueue")
	private BasicQueue<Clue> clueQueue;
	/*
	 * max multiple-thread number
	 */
	final Integer maxTopic = CrawlerConfig.getNum("MaxTopic");
	final Integer maxBlogger = CrawlerConfig.getNum("MaxBlogger");
	final Integer maxClue = CrawlerConfig.getNum("MaxClue");


	@Override
	void initialize() {
		taskExecutor = context.getBean(ThreadPoolTaskExecutor.class);
		TopicBusiness topicBusiness = context.getBean(TopicBusiness.class);
		BloggerBusiness bloggerBusiness = context
				.getBean(BloggerBusiness.class);
		ClueBusiness clueBusiness = context.getBean(ClueBusiness.class);
		List<Topic> topics = topicBusiness.getAll();
		List<Blogger> bloggers = bloggerBusiness.getAll();
		List<Clue> clues = clueBusiness.getAll();
		fillQueue(clueQueue, clues);
		fillQueue(topicQueue, topics);
		fillQueue(bloggerQueue, bloggers);
	}
	@Override
	void after(){
		taskExecutor.shutdown();
	}
	@Override
	<T> void fillQueue(BasicQueue<T> queue, List<T> ls) {
		if (!queue.isEmpty()) {
			getLogger().error("队列非空,系统异常");
			while (!queue.isEmpty())
				queue.pop();
		}
		int count = 0;
		for(T t:ls){
			try {
				Integer st = (Integer) PropertyUtils.getProperty(t, "operateStatus");
				if(st == 0){
					queue.push(t);
					count ++;
				}
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String message = "";
		if(ls.size()>0){
			message = ls.get(0).getClass().getName();
		}
		getLogger().info(message+"装配广搜队列："+count);

	}
	@Override
	void work() {
		for (int i = 0; i < Math.min(maxTopic, topicQueue.size()); i++) {
			SinaTopicWorker c = context.getBean(SinaTopicWorker.class);
			taskExecutor.execute(c);
		}
		for (int i = 0; i <Math.min(maxClue, clueQueue.size()) ; i++) {
			SinaClueWorker c = context.getBean(SinaClueWorker.class);
			taskExecutor.execute(c);
		}
		for (int i = 0; i <Math.min(maxBlogger, bloggerQueue.size()) ; i++) {
			SinaBloggerWorker c = context.getBean(SinaBloggerWorker.class);
			taskExecutor.execute(c);
		}
		waitThread(taskExecutor);
	}
}
