package com.vmojing.crawler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Startup {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CrawlerRootConfiguration.class);

	}
}
