package com.vmojing.util.tag;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.util.UtilRootConfiguration;
import com.vmojing.util.tag.TagFetchApp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilRootConfiguration.class})
public class TagAppTest {
	@Autowired
	TagFetchApp tagapp;
	@Test
	public void testapp(){
		tagapp.work();
	}
}
