package com.vmojing.core.fetcher;

import org.junit.Test;

import com.vmojing.core.CoreBeansConfiguration;
import com.vmojing.core.fetcher.api.TokenFetcher;
import com.vmojing.core.fetcher.api.TopicFetcher;
import com.vmojing.core.fetcher.impl.TokenFetcherImpl;

public class TokenFetcherTest {
	TokenFetcher tf = new TokenFetcherImpl();
	String userName = "wow_haigui@163.com";
	String passw = "vmojing";
	String appUrl1 = "https://api.weibo.com/2/oauth2/authorize?client_id=4290141482&display=code&response_type=token&redirect_uri=http://lvfoluo.com/v2/callback.html";
	String appUrl2 = "https://api.weibo.com/oauth2/authorize?client_id=245891426&response_type=code&redirect_uri=http://qiuzf.sinaapp.com/callback.php";
	String appUrl3 = "https://api.weibo.com/oauth2/authorize?response_type=token&client_id=2112368190&redirect_uri=http://apps.weibo.com/jiafensi";
	@Test
	public void testGetToken(){
		System.out.println(tf.getToken(userName,passw,appUrl1));
	}
}
