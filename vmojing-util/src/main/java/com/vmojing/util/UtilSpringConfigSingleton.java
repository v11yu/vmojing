package com.vmojing.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class UtilSpringConfigSingleton {
	private static UtilSpringConfigSingleton unique;
	private ApplicationContext context;
	private UtilSpringConfigSingleton(){
		context = new AnnotationConfigApplicationContext(UtilRootConfiguration.class);
	}
	public static ApplicationContext getContext(){
		if(unique == null){
			unique = new UtilSpringConfigSingleton();
		}
		return unique.context;
	}
	public static Object getBean(String name){
		return getContext().getBean(name);
	}
}
