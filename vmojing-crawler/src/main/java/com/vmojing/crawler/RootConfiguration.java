package com.vmojing.crawler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vmojing.mongodb.BeansConfiguration;
import com.vmojing.mongodb.MongoConfiguration;

@Configuration
@Import({ MongoConfiguration.class ,BeansConfiguration.class})
public class RootConfiguration {

}
