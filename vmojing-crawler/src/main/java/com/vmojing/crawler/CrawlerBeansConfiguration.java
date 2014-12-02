package com.vmojing.crawler;


import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.vmojing.crawler.fetcher.api.Loginer;
import com.vmojing.crawler.fetcher.mobile.MobileSinaLoginer;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;
import com.vmojing.crawler.queue.BasicQueue;
import com.vmojing.crawler.work.SinaBloggerWorker;
import com.vmojing.crawler.work.SinaClueWorker;
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
	 * crawler worker
	 * use autowired in class
	 */

}
