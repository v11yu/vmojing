package com.vmojing.crawler.parser;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.crawler.CrawlerRootConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={CrawlerRootConfiguration.class})
public class UserParserTest {
	@Autowired
	UserParser userParser;
}
