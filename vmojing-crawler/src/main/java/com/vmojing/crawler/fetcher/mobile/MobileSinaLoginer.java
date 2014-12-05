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
import org.springframework.stereotype.Component;

import com.vmojing.crawler.fetcher.BasicHttpMethod;
import com.vmojing.crawler.fetcher.api.Loginer;
/**
 * 新浪手机web端登陆器
 * @author v11
 * @date 2014年9月5日
 */

public class MobileSinaLoginer extends BasicHttpMethod implements Loginer{
	private final static Integer TRY_NUM = 3;
	private final String LOGIN_URL = "http://login.weibo.cn/login/";
	private HttpClient client ;
	private String userName = "wow_haigui2@163.com";
	private String password = "vmojing";
	public MobileSinaLoginer(){
	}
	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		HttpClient tmpClient = new DefaultHttpClient();
		HttpGet get = addHttpGetWithHeader(LOGIN_URL);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		String inputPwdName = null;
		String actionName = null;
		HttpResponse res = null;
		try {
			res = tmpClient.execute(get);
			Document doc = Jsoup.parse(getResponseBody(res));
			release(res);
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
    		getLogger().info("username:"+userName+" password:"+password);
    		formParams.add(new BasicNameValuePair("submit", "登录"));
    		UrlEncodedFormEntity fromEntity = new UrlEncodedFormEntity(formParams, "uTF-8");
			HttpPost post = addHttpPostWithHeader(LOGIN_URL+actionName);
			post.setEntity(fromEntity);
			res = tmpClient.execute(post);
			release(res);
			Header header = res.getFirstHeader("Location");
			get = addHttpGetWithHeader(header.getValue());
			res = tmpClient.execute(get);
			release(res);
//			get = addHttpGetWithHeader("http://weibo.com/u/1744649233");
//			res = tmpClient.execute(get);
//			getLogger().info(getResponseBody(res));
//			release(res);
			this.client = tmpClient;
			getLogger().info("Loginer登陆成功");
			return true;
			
		} catch (ClientProtocolException e) {
			getLogger().error(e.getMessage());
		} catch (IOException e) {
			getLogger().error(e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			getLogger().error(e.getMessage()+" "+e.getStackTrace());
		}
		finally{
			if(res != null){
				release(res);
			}
		}
		return false;
	}
	@Override
	public HttpClient getClient() {
		// TODO Auto-generated method stub
		if(client != null) return client;
		login();
		if(client == null){
			int i = 0;
			for(i=0;i<TRY_NUM&&!login();i++){
				getLogger().warn("httpClient登陆失败，正在尝试，尝试次数："+i+"休眠30s");
				try {
					Thread.sleep(30*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(i>TRY_NUM) return null;
		}
		return client;
	}

}
