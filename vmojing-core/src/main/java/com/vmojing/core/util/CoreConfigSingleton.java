package com.vmojing.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vmojing.core.CoreRootConfiguration;
import com.vmojing.mongodb.MongoRootConfiguration;

public class CoreConfigSingleton {
	private static CoreConfigSingleton unique;
	private ApplicationContext context;
	private CoreConfigSingleton(){
		context = new AnnotationConfigApplicationContext(CoreRootConfiguration.class);
	}
	public static ApplicationContext getContext(){
		if(unique == null){
			unique = new CoreConfigSingleton();
		}
		return unique.context;
	}
	public static Object getBean(String name){
		return getContext().getBean(name);
	}
}
