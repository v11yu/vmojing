package com.vmojing.core.fetcher;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicHttpMethod implements BasicHttp{
	private String RefererString = "www.baidu.com";
	private static Map<Class, Logger> loggers = new HashMap<Class, Logger>();
	
	protected synchronized Logger getLogger(){
		if(!BasicHttpMethod.loggers.containsKey(this.getClass())){
			Logger log = LoggerFactory.getLogger(this.getClass());
			BasicHttpMethod.loggers.put(this.getClass(), log);
		}
		return BasicHttpMethod.loggers.get(this.getClass());
		
	}

	public void release(HttpResponse res){
		try {
			EntityUtils.consume(res.getEntity());
		} catch (IOException e) {
			getLogger().error(e.toString());
		}
	}

	public HttpGet addHttpGetWithHeader(String url){
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
		httpGet.setHeader("Referer", RefererString);
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
		RefererString = url ;
		return httpGet;
	}

	public HttpPost addHttpPostWithHeader(String url){
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

	public String getResponseBody(HttpResponse res){
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

	public String getHeaders(HttpResponse response){
		Header[] headers = response.getAllHeaders();
		String res = "";
    	for(Header header:headers){
    		res += "key; "+header.getName()
    				+" value:"+header.getValue();
    	}
    	return res;
	}
	public List<NameValuePair> addHiddenInput(List<NameValuePair> formParams,Elements inputs){
		for(Element input : inputs){
			if(input.attr("type").equalsIgnoreCase("hidden")){
				formParams.add(new BasicNameValuePair(input.attr("name"), input.attr("value")));
			}
		}
		return formParams;
	}
}
