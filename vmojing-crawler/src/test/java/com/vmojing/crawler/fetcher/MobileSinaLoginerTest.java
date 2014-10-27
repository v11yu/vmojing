package com.vmojing.crawler.fetcher;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.crawler.CrawlerRootConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CrawlerRootConfiguration.class})
public class MobileSinaLoginerTest {
	@Autowired
	Loginer loginer;
	@Test
	public void testLogin(){
		assertTrue(loginer.login());
	}
	@Test
	public void testGetClient(){
		assertNotNull(loginer.getClient());
	}
}
