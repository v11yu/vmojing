package com.vmojing.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.vmojing.core.fetcher.api.Loginer;
import com.vmojing.core.fetcher.impl.MobileSinaLoginer;
import com.vmojing.core.fetcher.impl.MobileTopicFetcher;


@Configuration
public class CoreBeansConfiguration {
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
}
