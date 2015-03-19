package com.vmojing.crawler.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.vmojing.crawler.CrawlerRootConfiguration;


public class CrawlerConfigSingleton {
	private static CrawlerConfigSingleton unique;
	private ApplicationContext context;
	private CrawlerConfigSingleton(){
		context = new AnnotationConfigApplicationContext(CrawlerRootConfiguration.class);
	}
	public static ApplicationContext getContext(){
		if(unique == null){
			unique = new CrawlerConfigSingleton();
		}
		return unique.context;
	}
	public static Object getBean(String name){
		return getContext().getBean(name);
	}
}
