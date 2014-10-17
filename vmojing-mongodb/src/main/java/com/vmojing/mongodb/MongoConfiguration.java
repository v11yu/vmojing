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


/**
 * Date:   5/24/13 / 8:05 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * This is a example on how to setup a database with Spring's Java Configuration (JavaConfig) style.
 * <p/>
 * As you can see from the code below this is easy and a lot better then using the old style of XML files.
 * <p/>
 * T
 */

@Configuration
@EnableMongoRepositories
@ComponentScan("com.vmojing.mongodb")
@Import({ SpringConfiguration.class })
public class MongoConfiguration extends AbstractMongoConfiguration {


    @Override
    protected String getDatabaseName() {
        return "demo";
    }
    @Override
    public Mongo mongo() throws Exception {
    	
        return new Mongo(new ArrayList<ServerAddress>() {{ add(new ServerAddress("127.0.0.1", 27017));}});

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
