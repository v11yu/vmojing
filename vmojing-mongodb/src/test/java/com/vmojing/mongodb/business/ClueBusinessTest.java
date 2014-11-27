package com.vmojing.mongodb.business;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmojing.mongodb.MongoRootConfiguration;
import com.vmojing.mongodb.business.api.ClueBusiness;
import com.vmojing.mongodb.domain.Clue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRootConfiguration.class})
public class ClueBusinessTest {
	@Autowired
	ClueBusiness clueBusi;
	@Test
	public void testGetAll(){
		List<Clue> clues =clueBusi.getAll();
		for(Clue c : clues){
			System.out.println(c);
		}
	}
}
