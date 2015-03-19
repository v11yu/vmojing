package com.vmojing.util.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.domain.User;
import com.vmojing.util.UtilRootConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilRootConfiguration.class})
public class UserFetcherTest {
	@Autowired
	UserFetcher uf;
	String uid = "104335";
	@Test
	public void testFetch(){
		User u = uf.work(uid);
		System.out.println(u);
	}
}
