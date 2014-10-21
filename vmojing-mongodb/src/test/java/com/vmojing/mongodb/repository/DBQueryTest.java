package com.vmojing.mongodb.repository;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBQueryTest {
	@Test
	public void testCreate(){
		DBQuery query = new DBQuery();
		assertNotNull(query.getQuery());
	}
	@Test
	public void testAddOperation(){
		DBQuery query = new DBQuery();
		query.lessThan("key1", "value1");
		query.greaterThan("key2", "v2");
		query.equalsOperation("key3","v3");
		System.out.println(query.getQuery());
	}
}
