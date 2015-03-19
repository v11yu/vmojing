package com.vmojing.util.tag;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.domain.Tag;
import com.vmojing.util.UtilRootConfiguration;
import com.vmojing.util.tag.TagExport;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilRootConfiguration.class})
/**
 * 其实是用来输出标签的，用test的形式。运行一下标签文件就输出了.
 * 
 * @author v11
 */
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
