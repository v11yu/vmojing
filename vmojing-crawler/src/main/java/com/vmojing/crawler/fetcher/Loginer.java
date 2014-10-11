package com.vmojing.crawler.fetcher;

import org.apache.http.client.HttpClient;

/**
 * 登陆功能的接口
 * @author v11
 * @date 2014年9月5日
 * @Since 1.0
 */

public interface Loginer {
	/**
	 * 登陆操作
	 * @return 
	 * true 登陆成功
	 * <br/> false 登陆失败
	 */
	public boolean login();
	/**
	 * 获取登陆后的Client<br/>
	 * 必须先进行login操作<br/>
	 * @return 
	 */
	public HttpClient getClient();
}
