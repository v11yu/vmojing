package com.vmojing.crawler.fetcher;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
/**
 * 基础网页抓取器
 * @author v11
 * @date 2014年9月5日
 * @version 1.0
 */
public class BasicFetcher {
	private final Logger log = Logger.getLogger(this.getClass().getName());
	private final String LOGIN_URL = "http://login.weibo.cn/login/";
	private String RefererString = null;
	/**
	 * 释放掉res的资源，在每次连接后
	 * @param res
	 */
	protected void release(HttpResponse res){
		try {
			EntityUtils.consume(res.getEntity());
		} catch (IOException e) {
			log.info(e.toString());
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
		if(RefererString == null){
			httpGet.setHeader("Referer", LOGIN_URL);
		}
		else {
			httpGet.setHeader("Referer", RefererString);
		}
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
		if(RefererString == null){
			httpPost.setHeader("Referer", LOGIN_URL);
		}
		else {
			httpPost.setHeader("Referer", RefererString);
		}
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		RefererString = url ;
		return httpPost;
	}
	
	/**
	 * 打印出页面信息body+header
	 * @param res
	 */
	protected void showResponse(HttpResponse res){
		log.info(toStringHeaders(res));
		log.info("---------------------------------");
		log.info(toStringResponseBody(res));
	}
	/**
	 * response的页面信息
	 * @param res
	 */
	protected String toStringResponseBody(HttpResponse res){
		HttpEntity entity = res.getEntity();
		if(entity != null){
			String content;
			try {
				content = EntityUtils.toString(entity,"UTF-8");
				return content;
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * response出报文头信息
	 * @param response
	 */
	protected String toStringHeaders(HttpResponse response){
		String heads = "";
		Header[] headers = response.getAllHeaders();
    	for(Header header:headers){
    		heads += ("key; "+header.getName()
    				+" value:"+header.getValue());
    	}
    	return heads;
	}
}
