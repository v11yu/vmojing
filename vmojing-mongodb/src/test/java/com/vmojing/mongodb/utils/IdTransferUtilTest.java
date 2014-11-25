package com.vmojing.mongodb.utils;

import org.junit.Test;

public class IdTransferUtilTest {
	@Test
	public void testUrl2MId(){
		String url = "http://weibo.com/2142312651/BxT404Kbv?ref=home&rid=14_0_2666906032226306175&type=comment#_rnd1416896548980";

		String mid = IdTransferUtil.url2Mid(url);
		System.out.println(mid);
	}
}
