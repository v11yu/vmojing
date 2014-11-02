package com.vmojing.mongodb;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.vmojing.mongodb.domain.AccessToken;
import com.vmojing.mongodb.domain.Topic;
import com.vmojing.mongodb.repository.BasicRepository;
import com.vmojing.mongodb.repository.BasicRepositoryTest;
import com.vmojing.mongodb.utils.SpringConfigSingleton;

public class Demo {

	public static void main(String[] args) {

		
		BasicRepository<AccessToken> accessTokenDao = (BasicRepository<AccessToken>) SpringConfigSingleton.getContext().getBean("accessTokenDao");
	
		AccessToken at = new AccessToken();
		at.setToken("2.00Q2VpfF3mz1gE14574e94e1jqf6TD");
		try {
			accessTokenDao.saveAndUpdate(at);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
