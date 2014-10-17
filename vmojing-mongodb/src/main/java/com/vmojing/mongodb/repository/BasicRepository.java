package com.vmojing.mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;



public class BasicRepository<T> {
	@Autowired
    MongoTemplate mongoTemplate;
	private final Class<?> typeParameterClass;
	public BasicRepository(Class<?> typeParameterClass){
		this.typeParameterClass = typeParameterClass;
	}
	public void createCollection(T t) {
        if (!mongoTemplate.collectionExists(typeParameterClass)) {
            mongoTemplate.createCollection(typeParameterClass);
        }
    }
}
