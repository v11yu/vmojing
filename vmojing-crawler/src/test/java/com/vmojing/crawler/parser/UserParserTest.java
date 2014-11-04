package com.vmojing.crawler.parser;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.mongodb.business.api.UserBusiness;
import com.vmojing.mongodb.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={CrawlerRootConfiguration.class})
public class UserParserTest {
	@Autowired
	UserParser userParser;
	@Test
	public void testGetUser(){
		User u = userParser.getIdByName("v11yu");
		System.out.println(u);
		assertNotNull(u);
	}
	@Test
	public void testGetFans(){
		User u = userParser.getIdByName("v11yu");
		List<User> fans = userParser.getFans(u.getId());
		System.out.println("fans list size:"+fans.size());
	}
}
