package com.vmojing.crawler.work;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.mchange.util.AssertException;

public class SinaTopicWorkerTest {
	SinaTopicInitializer worker = new SinaTopicInitializer(null, null,null);
	Date d = new Date(0);
	@Test
	public void testGetBeginTime(){
		String begin = worker.getBeginTime(d);
		System.out.println("begin:"+begin);
		assertEquals(begin,"19700101");
	}
	@Test
	public void testGetEndTime(){
		String end = worker.getEndTimeFromBeginTime(d);
		System.out.println("end:"+end);
		assertEquals(end,"19700102");
	}
}
