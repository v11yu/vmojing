package com.vmojing.mongodb.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.business.AccessTokenAllocation;


public class AccessTokenAllocationTest {
	@Test
	public void testAccessToken(){
		String token = AccessTokenAllocation.getAccessToken();
		System.out.println(token);
	}
}
