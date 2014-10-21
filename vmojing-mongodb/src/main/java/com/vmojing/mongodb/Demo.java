package com.vmojing.mongodb;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicDao;
import com.vmojing.mongodb.repository.BasicRepository;
@PropertySource("classpath:mongodb.properties")
public class Demo {
	@Autowired
	Environment env;
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfiguration.class);
		BasicDao<Topic> b = new BasicDao<Topic>();
		b.show(new Topic());
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
