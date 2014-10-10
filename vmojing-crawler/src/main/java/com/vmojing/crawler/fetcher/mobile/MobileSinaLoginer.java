package com.vmojing.crawler.fetcher.mobile;

import java.io.IOException;




import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.vmojing.crawler.fetcher.Loginer;
/**
 * 新浪手机web端登陆器
 * @author v11
 * @date 2014年9月5日
 * @version 1.0
 */
public class MobileSinaLoginer implements Loginer{
	private final Logger log = Logger.getLogger(this.getClass().getName());
	private final String LOGIN_URL = "http://login.weibo.cn/login/";
	private String RefererString = null;
	private HttpClient client ;
	private String userName = "vmojing@163.com";
	private String password = "0744ict0744";
	public MobileSinaLoginer(){

	}
	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		client = new DefaultHttpClient();
		HttpGet get = addHttpGetWithHeader(LOGIN_URL);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		String inputPwdName = null;
		String actionName = null;
		try {
			HttpResponse res = client.execute(get);
			release(res);
			Document doc = Jsoup.parse(showResponseBody(res));
			Elements inputs = doc.getElementsByTag("input");
			Element form = doc.getElementsByTag("form").get(0);
			
			for(Element input : inputs){
				if(input.attr("type").equalsIgnoreCase("hidden")){
					formParams.add(new BasicNameValuePair(input.attr("name"), input.attr("value")));
				}
				else if(input.attr("type").equalsIgnoreCase("password")
						&&input.attr("name").startsWith("password_")){
					inputPwdName = input.attr("name");
				}
			}
			actionName = form.attr("action");
			formParams.add(new BasicNameValuePair("mobile", userName));
    		formParams.add(new BasicNameValuePair(inputPwdName, password));
    		formParams.add(new BasicNameValuePair("submit", "登录"));
    		UrlEncodedFormEntity fromEntity = new UrlEncodedFormEntity(formParams, "uTF-8");
			HttpPost post = addHttpPostWithHeader(LOGIN_URL+actionName);
			post.setEntity(fromEntity);
			res = client.execute(post);
			release(res);
			Header header = res.getFirstHeader("Location");
			get = addHttpGetWithHeader(header.getValue());
			res = client.execute(get);
			showResponse(res);
			release(res);
			get = addHttpGetWithHeader("http://weibo.com/u/1744649233");
			res = client.execute(get);
			showResponse(res);
			return true;
			
		} catch (ClientProtocolException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return false;
	}
	@Override
	public HttpClient getClient() {
		// TODO Auto-generated method stub
		return client;
	}
	
	/**
	 * 释放掉res的资源，在每次连接后
	 * @param res
	 */
	private void release(HttpResponse res){
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
	private HttpGet addHttpGetWithHeader(String url){
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
	private HttpPost addHttpPostWithHeader(String url){
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
	private void showResponse(HttpResponse res){
		showHeaders(res);
		showResponseBody(res);
	}
	/**
	 * 打印出response的页面信息
	 * @param res
	 */
	private String showResponseBody(HttpResponse res){
		HttpEntity entity = res.getEntity();
		if(entity != null){
			String content;
			try {
				content = EntityUtils.toString(entity,"UTF-8");
				log.debug(content);
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
	public void showHeaders(HttpResponse response){
		log.debug("---------------------------------");
		Header[] headers = response.getAllHeaders();
    	for(Header header:headers){
    		log.debug("key; "+header.getName()
    				+" value:"+header.getValue());
    	}
	}
	
	
	public static void main(String[] args) {
		MobileSinaLoginer login = new MobileSinaLoginer();
		
	}
}
