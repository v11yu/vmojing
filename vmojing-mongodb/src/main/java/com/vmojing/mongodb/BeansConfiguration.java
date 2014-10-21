package com.vmojing.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.DBConvertor;

@Configuration
public class BeansConfiguration {
	@Bean
	DBConvertor<Topic> topicConvertor(){
		return new DBConvertor<>(Topic.class);
	}
}
