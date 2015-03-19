package com.vmojing.core.fetcher.api;

public interface TokenFetcher {
	/**
	 * 获取新浪weiboAccessToken
	 * @param userName 用户名
	 * @param password 密码
	 * @param appUrl appUrl
	 * @return accessToken
	 */
	String getToken(String userName,String password,String appUrl);
}
