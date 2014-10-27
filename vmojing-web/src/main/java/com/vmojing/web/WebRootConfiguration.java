package com.vmojing.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vmojing.mongodb.MongoBeansConfiguration;
import com.vmojing.mongodb.MongoConfiguration;

@Configuration
@Import({WebAppConfiguration.class,MongoConfiguration.class ,MongoBeansConfiguration.class})
@ComponentScan({"com.vmojing.web","com.vmojing.mongodb"})
public class WebRootConfiguration {

}
