package com.vmojing.crawler;


import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.vmojing.crawler.fetcher.api.Loginer;
import com.vmojing.crawler.fetcher.mobile.MobileSinaLoginer;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;

@Configuration
public class CrawlerBeansConfiguration {
	/*
	 * PropertiesFactoryBean
	 */
	@Bean
	PropertiesFactoryBean myProperties(){
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocations(new ClassPathResource("crawler.properties"),
				new ClassPathResource("mongodb.properties"));
		return bean;
	}
	/*
	 * fetcher
	 */
	@Bean
	Loginer mobileSinaLoginer(){
		return new MobileSinaLoginer();
	}
	@Bean
	MobileTopicFetcher mobileTopicFetcher(){
		Loginer loginer = mobileSinaLoginer();
		if(loginer.getClient() == null){
			return null;
		}
		return new MobileTopicFetcher(loginer.getClient());
	}
	/*
	 * crawler
	 */
	
}
