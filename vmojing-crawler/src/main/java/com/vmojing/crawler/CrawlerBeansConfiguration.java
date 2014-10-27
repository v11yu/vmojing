package com.vmojing.crawler;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vmojing.crawler.fetcher.Loginer;
import com.vmojing.crawler.fetcher.mobile.MobileSinaLoginer;
import com.vmojing.crawler.fetcher.mobile.MobileTopicFetcher;

@Configuration
public class CrawlerBeansConfiguration {
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
}
