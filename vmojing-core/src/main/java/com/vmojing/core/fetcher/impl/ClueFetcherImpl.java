package com.vmojing.core.fetcher.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vmojing.core.fetcher.BasicHttpMethod;
import com.vmojing.core.fetcher.api.ClueFetcher;
import com.vmojing.core.fetcher.api.Loginer;
import com.vmojing.core.util.IdTransferUtil;
import com.vmojing.mongodb.domain.Weibo;
/**
 * 通过新浪手机端登陆，来抓取单条微博转发情况
 * 
 * @author v11
 */
public class ClueFetcherImpl extends BasicHttpMethod implements ClueFetcher{
	private HttpClient client;
	/**
	 * 
	 * @param loginClient 登陆后的Client
	 */
	public ClueFetcherImpl(HttpClient loginClient){
		client = loginClient;
	}
	
	String getMid(Element e){
		return e.toString().split("attitude/")[1].split("/")[0].trim();
	}

	String getContent(Element e){
		return e.text().split("赞\\[")[0].split(":")[1].trim();
	}
	String getUserName(Element e){
		return e.text().split(":")[0].trim();
	}
	Date getCreateAt(Element e){
		String str = e.text().split("来自")[0].split("赞\\[")[1].split("\\]")[1].trim();
		System.out.println(str);
		return null;
	}
	@Override
	public List<Weibo> getRepost(int page, String mid) {
		// TODO Auto-generated method stub
		List<Weibo> weibos = new ArrayList<Weibo>();
		String url = "http://weibo.cn/repost/"+mid+"?page="+page;
		HttpGet getSearch = addHttpGetWithHeader(url);
		HttpResponse res = null;
		try {
			res = client.execute(getSearch);
			String responseBodyString = getResponseBody(res);
			//getLogger().info(responseBodyString);
			Document doc = Jsoup.parse(responseBodyString);
			release(res);
			Elements es = doc.getElementsByClass("c");
			for(Element e: es){
				Elements eMid = e.getElementsByClass("cc");
				//没有包含mid
				if(eMid.size() == 0) continue;
				//热门回帖，格式不同，不统一处理
				if(e.getElementsByClass("kt").size() >0) continue;
				
				System.out.println(e.toString());
				System.out.println(e.text());
				String repostMid = getMid(eMid.get(0));
				Weibo w = new Weibo();
				w.setMid(repostMid);
				w.setId(IdTransferUtil.mid2Id(repostMid));
				w.setText(getContent(e));
				System.out.println(getUserName(e));
				getCreateAt(e);
				weibos.add(w);
			}
			//getLogger().info(responseBodyString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weibos;
	}

	@Override
	public List<String> getRepostMid(int page, String mid) {
		// TODO Auto-generated method stub
		List<String> mids = new ArrayList<String>();
		String url = "http://weibo.cn/repost/"+mid+"?page="+page;
		HttpGet getSearch = addHttpGetWithHeader(url);
		HttpResponse res = null;
		try {
			
			res = client.execute(getSearch);
			String responseBodyString = getResponseBody(res);
			//getLogger().info(responseBodyString);
			Document doc = Jsoup.parse(responseBodyString);
			release(res);
			Elements es = doc.getElementsByClass("cc");
			for(Element e: es){
				//String repostMid = e.toString().split("attitude/")[1].split("/")[0];
				String repostMid = getMid(e);
				mids.add(repostMid);
			}
			//getLogger().info(responseBodyString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mids;
	}
	@Override
	public int getPageCount(String mid) {
		// TODO Auto-generated method stub
		String url = "http://weibo.cn/repost/"+mid;
		HttpGet getSearch = addHttpGetWithHeader(url);
		HttpResponse res = null;
		try {
			
			res = client.execute(getSearch);
			String responseBodyString = getResponseBody(res);
			//getLogger().info(responseBodyString);
			Document doc = Jsoup.parse(responseBodyString);
			release(res);
			Elements es = doc.getElementsByClass("pa");
			for(Element e: es){
				String str = e.text().split("/")[1];
				return Integer.parseInt(str.substring(0, str.length()-1));
			}
			//getLogger().info(responseBodyString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static void main(String[] args) {
		String str = "淌糖唐瑭:求价格啊！！！！希望以后可以消费的起。（现实中认识我的不要告诉我妈，我转这种东西） 赞[0] 7分钟前 来自华为";
		String s1 = str.split("赞\\[")[0];
		System.out.println(s1);
//		Loginer login = new MobileSinaLoginer();
//		ClueFetcher clue  = new ClueFetcherImpl(login.getClient());
//		System.out.println(clue.getRepost(1, "C6Fuyv2HS"));
	}
}
