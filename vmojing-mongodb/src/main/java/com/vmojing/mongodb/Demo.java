package com.vmojing.mongodb;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicDao;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.TopicRepository;

public class Demo {
	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(
				MongoConfiguration.class);;

				BasicDao t = context
				.getBean(BasicDao.class);
				t.show();
		// cleanup person collection before insertion
		// personRepository.dropPersonCollection();

		// create person collection
//		topicRepository.createTopicCollection();

		// long startTime = System.currentTimeMillis();
		// for (int i = 0; i < 5; i++) {
		// topicRepository.insert(new Topic(new Date(),new
		// Date(),10,100,"hi",1));
		// }
	}
}
