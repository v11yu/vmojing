package com.vmojing.crawler.fetcher;
import static org.junit.Assert.*;
import org.junit.Test;

import com.vmojing.crawler.fetcher.util.IdTransferUtil;

public class IdTransferUtilTest {
	@Test
	public void testTransfer(){
		String mid = "BunWjnvGc";
		String id = IdTransferUtil.mid2Id(mid);
		System.out.println(id);
		assertEquals(id, "3772418675603324");
	}
}
