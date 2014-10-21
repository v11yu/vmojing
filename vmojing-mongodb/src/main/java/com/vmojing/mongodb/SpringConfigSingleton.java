package com.vmojing.mongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConfigSingleton {
	private static SpringConfigSingleton unique;
	private ApplicationContext context;
	private SpringConfigSingleton(){
		context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	}
	public static ApplicationContext getContext(){
		if(unique == null){
			unique = new SpringConfigSingleton();
		}
		return unique.context;
	}
}
