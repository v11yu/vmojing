package com.vmojing.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepository;

@Configuration
public class SpringConfiguration {
	@Bean
	public BasicRepository<Topic> topicDao(){
		return new BasicRepository<Topic>(Topic.class);
	}
	
}
