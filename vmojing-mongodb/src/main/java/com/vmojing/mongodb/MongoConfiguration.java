package com.vmojing.mongodb;

import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

import com.vmojing.mongodb.repository.*;
import com.vmojing.mongodb.utils.MongoDbUtil;



@Configuration
@EnableMongoRepositories

public class MongoConfiguration extends AbstractMongoConfiguration {


    @Override
    protected String getDatabaseName() {
        return "demo";
    }
    @Override
    public Mongo mongo() throws Exception {
    	return MongoDbUtil.getMongo();

    }
    @Override
    protected String getMappingBasePackage() {
        return "com.vmojing.mongodb.domain";
    }
    /**
     * can remove _class
     */
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		// remove _class
		MappingMongoConverter converter = new MappingMongoConverter(
				mongoDbFactory(), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(),
				converter);
		return mongoTemplate;

	}
	
}
