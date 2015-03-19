package com.vmojing.core.fetcher;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.core.CoreRootConfiguration;
import com.vmojing.core.fetcher.api.Loginer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreRootConfiguration.class})
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
