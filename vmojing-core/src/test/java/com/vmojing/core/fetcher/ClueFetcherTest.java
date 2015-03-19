package com.vmojing.core.fetcher;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.core.fetcher.api.ClueFetcher;
import com.vmojing.core.fetcher.api.Loginer;
import com.vmojing.core.fetcher.impl.ClueFetcherImpl;
import com.vmojing.core.fetcher.impl.MobileSinaLoginer;

public class ClueFetcherTest {
	Loginer login = new MobileSinaLoginer();
	Logger log = LoggerFactory.getLogger(ClueFetcherTest.class);
	ClueFetcher clue;
	@Before
	public void init(){
		log.error("test");
		clue = new ClueFetcherImpl(login.getClient());
	}
//	@Test
//	public void testGet(){
//		System.out.println(clue.getRepostMid(1, "C6Fuyv2HS"));
//	}
//	@Test
//	public void testCountPage(){
//		System.out.println(clue.getPageCount("C6Fuyv2HS"));
//	}
	@Test
	public void testGet(){
		System.out.println(clue.getRepost(1, "C6Fuyv2HS"));
	}
}
