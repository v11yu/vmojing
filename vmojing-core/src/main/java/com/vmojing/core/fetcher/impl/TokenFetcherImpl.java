package com.vmojing.core.fetcher.impl;

import java.io.IOException;




import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vmojing.core.fetcher.BasicHttpMethod;
import com.vmojing.core.fetcher.api.TokenFetcher;

public class TokenFetcherImpl extends BasicHttpMethod implements TokenFetcher{
	String postUrl = "https://api.weibo.com/oauth2/authorize";
	@Override
	public String getToken(String userName, String password, String appUrl) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = addHttpGetWithHeader(appUrl);
		HttpResponse res = null;
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		try {
			res = client.execute(get);
			String resString = getResponseBody(res);
			Document doc = Jsoup.parse(resString);
			//getLogger().info(doc.text());
			//getLogger().info(resString);
			release(res);
			Elements inputs = doc.getElementsByTag("input");
			formParams = addHiddenInput(formParams, inputs);
			formParams.add(new BasicNameValuePair("userId", userName));
			formParams.add(new BasicNameValuePair("passwd", password));
			UrlEncodedFormEntity fromEntity = new UrlEncodedFormEntity(formParams, "uTF-8");
			HttpPost post = addHttpPostWithHeader(postUrl);
			post.setEntity(fromEntity);
			res = client.execute(post);
//			String reurl;
//			while((reurl = toLocation(res))!=null){
//				printResponse(res);
//				release(res);
//				res = client.execute(addHttpPostWithHeader(reurl));
//			}
			String headString = getHeaders(res);
			printResponse(res);
			release(res);
			return getTokenFromHeader(headString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
	String getTokenFromHeader(String str){
		String ls[] = str.split("access_token=");
		if(ls.length>=2){
			return ls[1].split("&")[0];
		}
		return null;
		
	}

}
