package com.vmojing.crawler;

import java.util.List;
import java.util.Properties;
import java.util.Queue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.queue.*;
import com.vmojing.crawler.work.SinaBloggerWorker;
import com.vmojing.crawler.work.SinaClueWorker;
import com.vmojing.crawler.work.SinaTopicWorker;
import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.business.api.*;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;

@Component
public class CrawlerClient {
	/*
	 * logger
	 */
	private final static Logger log = LoggerFactory
			.getLogger(CrawlerClient.class);
	@Autowired
	ApplicationContext context;
	@Autowired
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
	final Integer maxTopic;
	final Integer maxBlogger;
	final Integer maxClue;

	@Autowired
	public CrawlerClient(
			@Qualifier("crawlerProperties") Properties crawlerProperties) {
		this.maxTopic = Integer.parseInt(crawlerProperties
				.getProperty("MaxTopic"));
		this.maxBlogger = Integer.parseInt(crawlerProperties
				.getProperty("MaxBlogger"));
		this.maxClue = Integer.parseInt(crawlerProperties
				.getProperty("MaxClue"));
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CrawlerRootConfiguration.class);
		CrawlerClient client = context.getBean(CrawlerClient.class);
		client.work();
	}

	private void initialize() {
		TopicBusiness topicBusiness = context.getBean(TopicBusiness.class);
		BloggerBusiness bloggerBusiness = context
				.getBean(BloggerBusiness.class);
		ClueBusiness clueBusiness = context.getBean(ClueBusiness.class);
		List<Topic> topics = topicBusiness.getAll();
		List<Blogger> bloggers = bloggerBusiness.getAll();
		List<Clue> clues = clueBusiness.getAll();
		checkAndFill(clueQueue, clues);
		checkAndFill(topicQueue, topics);
		checkAndFill(bloggerQueue, bloggers);
	}

	private <T> void checkAndFill(BasicQueue<T> queue, List<T> ls) {
		if (!queue.isEmpty()) {
			log.error("队列非空,系统异常");
			while (!queue.isEmpty())
				queue.pop();
		}
		log.info("装配广搜队列："+ls.size());
		queue.addAll(ls);
	}

	public void work() {
//		while (true) {
			initialize();
			for (int i = 0; i < maxTopic; i++) {
				SinaTopicWorker c = context.getBean(SinaTopicWorker.class);
				taskExecutor.execute(c);
			}
			for (int i = 0; i < maxClue; i++) {
				SinaClueWorker c = context.getBean(SinaClueWorker.class);
				taskExecutor.execute(c);
			}
			for (int i = 0; i < maxBlogger; i++) {
				SinaBloggerWorker c = context.getBean(SinaBloggerWorker.class);
				taskExecutor.execute(c);
			}
			while (taskExecutor.getActiveCount() > 0) {
				log.info("thread active count:" + taskExecutor.getActiveCount());
				sleep(30);
			}
			log.info("一次work结束");
			taskExecutor.shutdown();
//		}
		//int t = taskExecutor.getActiveCount();
		
	}
	private void sleep(int second){
		try {
			Thread.sleep(1000*second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
