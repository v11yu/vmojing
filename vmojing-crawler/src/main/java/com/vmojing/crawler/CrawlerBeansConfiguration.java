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
import com.vmojing.crawler.fetcher.api.Loginer;
import com.vmojing.crawler.fetcher.mobile.MobileSinaLoginer;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;
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
	 * fetcher
	 */
	@Bean
	@Scope("prototype")
	Loginer mobileSinaLoginer(){
		return new MobileSinaLoginer();
	}
	@Bean
	@Scope("prototype")
	MobileTopicFetcher mobileTopicFetcher(){
		Loginer loginer = mobileSinaLoginer();
		if(loginer.getClient() == null){
			return null;
		}
		return new MobileTopicFetcher(loginer.getClient());
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
	
	/*
	 * scheduler
	 */

	@Autowired
	CrawlerClient mainClient;
	@Bean
	MethodInvokingJobDetailFactoryBean mainClientJobDetail(){
		MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
		bean.setTargetObject(mainClient);
		bean.setTargetMethod("start");
		bean.setConcurrent(false);
		return bean;
	}
	@Bean
	SimpleTriggerFactoryBean mainClientTrigger(){
		SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
		JobDetail job = mainClientJobDetail().getObject();
		bean.setJobDetail(job);
		bean.setStartDelay(1000);
		bean.setRepeatInterval(1000*60*10); // 1hour
		return bean;
	}
	@Autowired
	TopicInitClient initClient;
	@Bean
	MethodInvokingJobDetailFactoryBean initClientJobDetail(){
		MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
		bean.setTargetObject(initClient);
		bean.setTargetMethod("start");
		bean.setConcurrent(false);
		return bean;
	}
	@Bean
	SimpleTriggerFactoryBean initClientTrigger(){
		SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
		JobDetail job = initClientJobDetail().getObject();
		bean.setJobDetail(job);
		bean.setStartDelay(1000*60*10);
		bean.setRepeatInterval(1000*60*60); // 1hour
		return bean;
	}
	@Bean
	SchedulerFactoryBean scheduler(){
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		JobDetail job1 = mainClientJobDetail().getObject();
		Trigger trigger1 = mainClientTrigger().getObject();
		JobDetail job2 = initClientJobDetail().getObject();
		Trigger trigger2 = initClientTrigger().getObject();
		bean.setJobDetails(job1,job2);
		bean.setTriggers(trigger1,trigger2);
		return bean;
	}

}
