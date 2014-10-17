package com.vmojing.crawler.fetcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmojing.dao.impl.BasicDAO;

public abstract class AbstractBasicFetcher {
	@SuppressWarnings("rawtypes")
	/** 登陆日志 */
	private static final Map<Class,Logger> loggers = new HashMap<Class,Logger>();
	/** httpClient */
	private HttpClient client;
	/** 记录上一次Clent请求的页面地址 */
	private String RefererString = "http://login.weibo.cn/login/";
	
	public AbstractBasicFetcher(){
		client = getLoginHttpClient();
	}
	protected abstract HttpClient getLoginHttpClient();
	/**
	 * 获取当前日志
	 * @return 当前类日志
	 */
	protected Logger getLogger()
	{
	    Logger logger = null;
	    if (AbstractBasicFetcher.loggers.containsKey(this.getClass())) {
	        logger = AbstractBasicFetcher.loggers.get(this.getClass());
	    } else {
	        logger = LoggerFactory.getLogger(this.getClass());
	        AbstractBasicFetcher.loggers.put (this.getClass(), logger);
	    }
	    return logger;
	}
	/**
	 * 释放掉res的资源，在每次连接后
	 * @param res
	 */
	protected void release(HttpResponse res){
		try {
			EntityUtils.consume(res.getEntity());
		} catch (IOException e) {
			getLogger().info("release the HttpResPonse failure"+e.toString());
		}
	}
	/**
	 * 为HttpGet添加报文头信息
	 * @param url
	 * @return 
	 */
	protected HttpGet addHttpGetWithHeader(String url){
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
		httpGet.setHeader("Referer", RefererString);
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
		RefererString = url ;
		return httpGet;
	}
	/**
	 * 添加post请求报文头信息
	 * @param url
	 * @return
	 */
	protected HttpPost addHttpPostWithHeader(String url){
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
		httpPost.setHeader("Referer", RefererString);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		RefererString = url ;
		return httpPost;
	}
	
}
