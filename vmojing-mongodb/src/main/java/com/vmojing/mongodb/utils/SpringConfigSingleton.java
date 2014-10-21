package com.vmojing.mongodb.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vmojing.mongodb.RootConfiguration;

public class SpringConfigSingleton {
	private static SpringConfigSingleton unique;
	private ApplicationContext context;
	private SpringConfigSingleton(){
		context = new AnnotationConfigApplicationContext(RootConfiguration.class);
	}
	public static ApplicationContext getContext(){
		if(unique == null){
			unique = new SpringConfigSingleton();
		}
		return unique.context;
	}
}
