package com.vmojing.util.authorize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.domain.AccessToken;
import com.vmojing.util.UtilRootConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AuthorizeUtilRootConfiguration.class})
public class AuthorizeAppTest {
	@Autowired
	AuthorizeApp app;
	@Before
	public void before(){
		app.readUserInfo();
	}
	@Test 
	public void testRead(){
		System.out.println(app.user.size());
		System.out.println(app.appUrls.size());
	}
	@Test
	public void testApp(){
		app.work();
		for(AccessToken at : app.tokens){
			System.out.println(at);
		}
	}
}
