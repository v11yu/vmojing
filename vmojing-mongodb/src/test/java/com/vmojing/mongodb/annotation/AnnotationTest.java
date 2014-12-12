package com.vmojing.mongodb.annotation;

import java.lang.reflect.Field;

import org.junit.Test;
import static org.junit.Assert.*;
import com.vmojing.mongodb.domain.Topic;

public class AnnotationTest {
	Topic t = new Topic();
	@Test
	public void testFindAnnotation(){
		for(Field f : t.getClass().getDeclaredFields()){
			if(f.isAnnotationPresent(Frequent.class)){
				assertEquals(f.getName(), "updateFrequency");
				System.out.println(f.getName());
			}
		}
	}
	
}
