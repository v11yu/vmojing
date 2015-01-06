package com.vmojing.util.tool;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.domain.Tag;
import com.vmojing.util.UtilRootConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilRootConfiguration.class})
public class TagExportTest {
	@Autowired
	TagExport te;
	@Test
	public void testTags(){
		List<Tag> ts = te.getListFromDb();
		
		System.out.println(ts.size());
	}
	@Test
	public void testWork(){
		te.work();
	}
}
