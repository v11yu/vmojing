package com.vmojing.mongodb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.vmojing.mongodb.domain.Topic;


@Repository
public class TopicRepository {
	@Autowired
    MongoTemplate mongoTemplate;
	public void insert(Topic t) {
		//mongoTemplate.

        mongoTemplate.insert(t);
    }
	public void createTopicCollection() {
        if (!mongoTemplate.collectionExists(Topic.class)) {
            mongoTemplate.createCollection(Topic.class);
        }
    }
}
