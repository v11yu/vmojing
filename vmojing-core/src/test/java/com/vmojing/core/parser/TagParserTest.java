package com.vmojing.core.parser;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import com.vmojing.core.CoreRootConfiguration;
import com.vmojing.core.parser.api.TagParser;
import com.vmojing.mongodb.domain.Tag;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={CoreRootConfiguration.class})
public class TagParserTest {
	@Autowired
	TagParser tagParser;
//	@Test
//	public void testTag(){
//		System.out.println(tagParser.getTag("acm"));
//	}
	@Test
	public void testTags(){
		String uids = "1639498782"+","+"2146857211";
		List<Tag> tl = tagParser.getTagsFromUsers(uids);
		for(Tag t:tl) System.out.println(t);
	}
}
