package com.vmojing.crawler;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vmojing.crawler.queue.BloggerQueue;
import com.vmojing.crawler.queue.ClueQueue;
import com.vmojing.crawler.queue.TopicQueue;
import com.vmojing.mongodb.business.api.*;
import com.vmojing.mongodb.domain.Blogger;
import com.vmojing.mongodb.domain.Clue;
import com.vmojing.mongodb.domain.Topic;

@Component

public class CrawlerClient {
	@Autowired
	private TopicBusiness topicBusiness;
//	@Autowired
//	private BloggerBusiness bloggerBusiness;
//	@Autowired
//	private ClueBusiness clueBusiness;
	@Resource(name="myProperties")
	public Properties myProperties;
	public static void main(String[] args) {
		//initialize();
	}
//	@PostConstruct
//	private void initialize(){
//		List<Topic> topics = topicBusiness.getAll();
//		List<Blogger> bloggers = bloggerBusiness.getAll();
//		List<Clue> clues = clueBusiness.getAll();
//		ClueQueue.addAll(clues);
//		TopicQueue.addAll(topics);
//		BloggerQueue.addAll(bloggers);
//	}
//	public void work(){
//		while(TopicQueue.isEmpty()){
//			Topic t = TopicQueue.pop();
//			//do something about topic
//			//add something to queue
//		}
//	}
}
