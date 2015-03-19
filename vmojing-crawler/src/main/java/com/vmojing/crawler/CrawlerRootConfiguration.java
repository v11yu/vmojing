package com.vmojing.crawler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vmojing.core.CoreBeansConfiguration;
import com.vmojing.mongodb.MongoBeansConfiguration;
import com.vmojing.mongodb.MongoConfiguration;

@Configuration
@ComponentScan({"com.vmojing.crawler","com.vmojing.mongodb","com.vmojing.core"})
@Import({ MongoConfiguration.class ,CoreBeansConfiguration.class,MongoBeansConfiguration.class,CrawlerBeansConfiguration.class})
public class CrawlerRootConfiguration {

}
