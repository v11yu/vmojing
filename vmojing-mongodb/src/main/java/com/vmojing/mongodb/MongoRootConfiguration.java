package com.vmojing.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepositoryTest;

@Configuration
@Import({ MongoConfiguration.class ,MongoBeansConfiguration.class})
@ComponentScan("com.vmojing.mongodb")
public class MongoRootConfiguration {

	
}
