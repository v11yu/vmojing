package com.vmojing.mongodb.business;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.business.api.UserBusiness;
import com.vmojing.mongodb.domain.User;
import com.vmojing.mongodb.repository.BasicRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRootConfiguration.class})
public class UserBusinessTest {
	@Autowired
	UserBusiness userBusiness;
	@Autowired
	@Qualifier("userDao")
	BasicRepository<User> userDao;
	@Before
	public void initialize(){
		userDao.dropAll();
	}
	@Test
	public void testCheckFans(){
		
	}
}
