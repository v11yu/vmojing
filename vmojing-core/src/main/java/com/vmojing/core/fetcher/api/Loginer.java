package com.vmojing.core.fetcher.api;

import org.apache.http.client.HttpClient;

/**
 * 登陆功能的接口
 * @author v11
 * @date 2014年9月5日
 */

public interface Loginer {
	/**
	 * 登陆操作
	 * @return 
	 * true 登陆成功
	 * <br/> false 登陆失败
	 */
	boolean login();
	/**
	 * 获取登陆后的Client<br/>
	 * 必须先进行login操作<br/>
	 * @return 
	 */
	HttpClient getClient();
}
