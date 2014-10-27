package com.vmojing.crawler.fetcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicHttpMethod {
	private String RefererString = "www.baidu.com";
	private static Map<Class, Logger> loggers = new HashMap<Class, Logger>();
	
	protected synchronized Logger getLogger(){
		if(!BasicHttpMethod.loggers.containsKey(this.getClass())){
			Logger log = LoggerFactory.getLogger(this.getClass());
			BasicHttpMethod.loggers.put(this.getClass(), log);
		}
		return BasicHttpMethod.loggers.get(this.getClass());
		
	}
	/**
	 * 释放资源
	 * @param res
	 */
	protected void release(HttpResponse res){
		try {
			EntityUtils.consume(res.getEntity());
		} catch (IOException e) {
			getLogger().error(e.toString());
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
	protected void printResponse(HttpResponse res){
		getHeaders(res);
		getResponseBody(res);
	}
	/**
	 * 打印出response的页面信息
	 * @param res
	 */
	protected String getResponseBody(HttpResponse res){
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
	 * 打印response出报文头信息
	 * @param response
	 */
	protected String getHeaders(HttpResponse response){
		Header[] headers = response.getAllHeaders();
		String res = "";
    	for(Header header:headers){
    		res += "key; "+header.getName()
    				+" value:"+header.getValue();
    	}
    	return res;
	}
}
