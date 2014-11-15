package com.vmojing.crawler.parser;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.crawler.CrawlerRootConfiguration;
import com.vmojing.crawler.fetcher.util.IdTransferUtil;
import com.vmojing.crawler.parser.api.CommentParser;
import com.vmojing.mongodb.domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={CrawlerRootConfiguration.class})
public class CommentParserTest {
	@Autowired
	CommentParser commentParser;
	private String wid;
	private Date lastUpdateTime;
	
	@Before
	public void initialize(){
		wid = IdTransferUtil.mid2Id("BtPJeoXzD");
		Date nowTime = new Date();
		lastUpdateTime = new Date(nowTime.getYear(), nowTime.getMonth(), nowTime.getDate());
	}
	@Test
	public void testGetComment(){
		
		List<Comment> ls = commentParser.getComment(wid, lastUpdateTime);
		assertNotNull(ls);
		System.out.println("commet :"+ls.size());
		assertTrue(ls.size()>0);
	}
}
