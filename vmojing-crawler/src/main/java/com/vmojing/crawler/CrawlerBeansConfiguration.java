package com.vmojing.crawler;


import java.io.IOException;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.vmojing.crawler.client.CrawlerClient;
import com.vmojing.crawler.client.TopicInitClient;

import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.work.SinaBloggerWorker;
import com.vmojing.crawler.work.SinaClueWorker;
import com.vmojing.crawler.work.check.CheckStrategy;
import com.vmojing.crawler.work.check.NoChecker;
import com.vmojing.crawler.work.check.WorkChecker;
import com.vmojing.crawler.work.interval.SimpleIntervalStrategy;
import com.vmojing.crawler.work.push.NoPush;
import com.vmojing.crawler.work.push.PushStrategy;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;

@Configuration
public class CrawlerBeansConfiguration {
	/*
	 * PropertiesFactoryBean
	 */
	@Bean
	PropertiesFactoryBean crawlerProperties(){
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocations(new ClassPathResource("crawler.properties"),
				new ClassPathResource("mongodb.properties"));
		return bean;
	}
	
	/*
	 * crawler queue
	 */
	@Bean
	@Scope("singleton")
	BasicQueue<Topic> topicQueue(){
		return new BasicQueue<Topic>();
	}
	@Bean
	BasicQueue<Blogger> bloggerQueue(){
		return new BasicQueue<Blogger>();
	}
	@Bean
	BasicQueue<Clue> clueQueue(){
		return new BasicQueue<Clue>();
	}
	@Bean
	BasicQueue<Topic> initTopicQueue(){
		return new BasicQueue<Topic>();
	}
	/*
	 * thread pool
	 */
	@Bean
	@Scope("prototype")
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(100);
		pool.setMaxPoolSize(1000);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}
	/*
	 * push strategy
	 */
	@Bean
	@Scope("prototype")
	PushStrategy noPush(){
		return new NoPush();
	}
	/*
	 * check strategy
	 */
	@Bean
	@Scope("prototype")
	CheckStrategy noCheck(){
		return new NoChecker();
	}

	@Bean
	@Scope("prototype")
	CheckStrategy workCheck(){
		return new WorkChecker();
	}
	/*
	 * interval strategy
	 */
	@Bean
	@Scope("prototype")
	SimpleIntervalStrategy simpleInterval(){
		return new SimpleIntervalStrategy();
	}
	
	

}
